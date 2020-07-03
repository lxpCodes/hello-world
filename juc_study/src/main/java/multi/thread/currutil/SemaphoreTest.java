package multi.thread.currutil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @ClassName SemaphoreTest
 * @Description Semaphore用法测试
 * @Author liangxp
 * @Date 2020/6/17 16:15
 **/
public class SemaphoreTest {
    private static final int THREAD_COUNT = 30;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + " Save data");
                        Thread.sleep(500L);

                        System.out.println(Thread.currentThread().getName() + ":availablePermits:" + semaphore.availablePermits());
                        System.out.println(Thread.currentThread().getName() + ":getQueueLength:" + semaphore.getQueueLength());
                        System.out.println(Thread.currentThread().getName() + ":hasQueuedThreads:" + semaphore.hasQueuedThreads());

                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        threadPool.shutdown();
    }
}
