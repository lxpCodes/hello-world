package multi.thread.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName AtomicIntegerTest2
 * @Description TODO
 * @Author liangxp
 * @Date 2020/6/16 18:03
 **/
public class AtomicIntegerTest2 {

    public static AtomicInteger count = new AtomicInteger(0);

    public static void inc(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException{
        final CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 190; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    inc();
                    countDownLatch.countDown();
                }
            }).start();
        }

        countDownLatch.await();
        System.out.println(count.get());
    }
}
