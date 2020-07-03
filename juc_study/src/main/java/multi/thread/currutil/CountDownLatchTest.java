package multi.thread.currutil;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName CountDownLatchTest
 * @Description TODO
 * @Author liangxp
 * @Date 2020/6/17 9:09
 **/
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 120; i++) {
            countDownLatch.countDown();
        }


        countDownLatch.await();

        System.out.println(countDownLatch.getCount());
    }
}
