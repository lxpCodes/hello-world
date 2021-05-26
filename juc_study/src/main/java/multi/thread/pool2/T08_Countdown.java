package multi.thread.pool2;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName T08_Countdown
 * @Description counddownlatch实现交替打印
 * @Author liangxp
 * @Date 2021/4/23 11:35
 **/
public class T08_Countdown {
    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args)  {
        final Object o = new Object();
        char[] c1 = "1234567".toCharArray();
        char[] c2 = "ABCDEFG".toCharArray();

        new Thread(() ->{
            synchronized (o){
                for (char c : c1) {
                    System.out.println(Thread.currentThread().getName() + "--" + c);
                    try {
                        latch.countDown();
                        o.notify();
                        o.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        },"t1").start();

        new Thread(() ->{
            synchronized (o){
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (char c : c2) {
                    System.out.println(Thread.currentThread().getName() + "--" + c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        },"t2").start();
    }
}
