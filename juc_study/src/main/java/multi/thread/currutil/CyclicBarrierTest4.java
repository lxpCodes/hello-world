package multi.thread.currutil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName CyclicBarrierTest4
 * @Description 测试isBroken方法
 * @Author liangxp
 * @Date 2020/6/17 16:09
 **/
public class CyclicBarrierTest4 {
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
//        thread.interrupt();
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(cyclicBarrier.isBroken());
    }
}
