package multi.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @ClassName TwinsLockTest
 * @Description 测试TwinsLock
 * @Author liangxp
 * @Date 2021/3/2 16:22
 **/
public class TwinsLockTest {
    public static void main(String[] args) throws InterruptedException {
        final Lock lock = new TwinsLock();

        class Worker extends Thread{
            public void run(){
                while (true){
                    lock.lock();
                    try{
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        // 启动10个线程
        for (int i = 0; i < 10; i++) {
            Worker worker = new Worker();
            worker.setDaemon(true);
            worker.start();
        }

        // 每隔1秒换行
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("===============");

        }
    }
}
