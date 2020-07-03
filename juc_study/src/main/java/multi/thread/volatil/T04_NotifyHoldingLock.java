package multi.thread.volatil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName T01_WithoutVolatile
 * @Description TODO
 * @Author liangxp
 * @Date 2020/6/25 23:00
 **/
public class T04_NotifyHoldingLock {
    volatile List list = new ArrayList();

    public void add(Object obj){
        list.add(obj);
    }

    public int getSize(){
        return list.size();
    }

    public static void main(String[] args) {
        T04_NotifyHoldingLock c = new T04_NotifyHoldingLock();
        final Object lock = new Object();


        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2 启动");
                if (c.getSize() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("t2 结束");

            }
            lock.notify();
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("t1 启动");
            synchronized (lock){
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add " + i);
                    if (c.getSize() == 5){
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        },"t1").start();
    }

}
