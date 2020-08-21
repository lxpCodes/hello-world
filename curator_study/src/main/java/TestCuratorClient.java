import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * @ClassName TestCuratorClient
 * @Description Curator API
 * @Author liangxp
 * @Date 2020/8/20 19:46
 **/
public class TestCuratorClient {
    public static void main(String[] args) throws Exception {
        // zk地址
        String zkAddress = "127.0.0.1:2181";
        // 重试策略 连不上zk集群重试三次，重试间隔递增
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        // 创建Curator Client并启动，启动成功可以与zk进行交互
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkAddress,retryPolicy);
        client.start();

        // create()方法创建ZNode，可以调用额外方法设置节点类型添加watcher
        String path = client.create().withMode(CreateMode.PERSISTENT).forPath("/user", "test".getBytes());
        System.out.println("创建成功:" + path);
        // 检查节点是否存在
        Stat stat = client.checkExists().forPath("/user");
        System.out.println("是否存在:" + stat != null);

        // 获取节点内容
        byte[] bytes = client.getData().forPath("/user");
        System.out.println("节点内容:" + new String(bytes));

        // 在user在创建多个临时顺序节点
        for (int i = 0; i < 3; i++) {
            client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/user/child-");
        }

        // 获取子节点
        List<String> children = client.getChildren().forPath("/user");
        System.out.println("子节点:" + children);

        client.delete().deletingChildrenIfNeeded().forPath("/user");

    }
}
