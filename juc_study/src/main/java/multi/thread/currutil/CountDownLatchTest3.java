package multi.thread.currutil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CountDownLatchTest3
 * @Description TODO
 * @Author liangxp
 * @Date 2020/6/17 15:35
 **/
public class CountDownLatchTest3 {
    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException{

        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(1);
                countDownLatch.countDown();
                try {
                    Thread.sleep(6000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(2);
                countDownLatch.countDown();
            }
        }).start();

//        countDownLatch.await(5, TimeUnit.SECONDS);
        countDownLatch.await();
        System.out.println(3);
    }
}
