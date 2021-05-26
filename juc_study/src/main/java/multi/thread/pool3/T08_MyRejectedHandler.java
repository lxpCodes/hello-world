package multi.thread.pool3;

import java.util.concurrent.*;

/**
 * @ClassName T08_MyRejectedHandler
 * @Description 自定义拒绝策略
 * @Author liangxp
 * @Date 2021/4/27 14:38
 **/
public class T08_MyRejectedHandler {
    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(4,4,
                0, TimeUnit.SECONDS,new ArrayBlockingQueue<>(6),
                Executors.defaultThreadFactory(),
                new MyHandler());
    }

    static class MyHandler implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (executor.getQueue().size() < 10000){
                //重试
                executor.execute(r);
            }
        }
    }
}
