import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;

import java.util.List;

/**
 * @ClassName TestCuratorWatcher
 * @Description zk watcher监听机制
 *              Watcher 监听机制是 ZooKeeper 中非常重要的特性，可以监听某个节点上发生的特定事件，
 *              例如，监听节点数据变更、节点删除、子节点状态变更等事件。
 *              当相应事件发生时，ZooKeeper 会产生一个 Watcher 事件，并且发送到客户端。
 *              通过 Watcher 机制，就可以使用 ZooKeeper 实现分布式锁、集群管理等功能。
 * @Author liangxp
 * @Date 2020/8/21 10:02
 **/
public class TestCuratorWatcher {

    public static void main(String[] args) throws Exception {
        // zk地址
        String zkAddress = "127.0.0.1:2181";
        // 重试策略 连不上zk集群重试三次，重试间隔递增
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        // 创建Curator Client并启动，启动成功可以与zk进行交互
        final CuratorFramework client = CuratorFrameworkFactory.newClient(zkAddress,retryPolicy);
        client.start();

        try {
            client.create().withMode(CreateMode.PERSISTENT)
                    .forPath("/user","test".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 在user在创建多个临时顺序节点
        for (int i = 0; i < 3; i++) {
            client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/user/child-");
        }

        // 这里通过usingWatcher()方法添加一个watcher
        List<String> children =  client.getChildren().usingWatcher(new CuratorWatcher() {
            @Override
            public void process(WatchedEvent watchedEvent) throws Exception {
                System.out.println("监听事件:type=" +watchedEvent.getType() + ",path=" + watchedEvent.getPath());
            }
        }).forPath("/user");

        System.out.println("children:" + children);
//        System.in.read();

        client.delete().deletingChildrenIfNeeded().forPath("/user");
    }
}
