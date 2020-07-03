package multi.thread.volatil;
import	java.util.concurrent.Semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName T01_WithoutVolatile
 * @Description TODO
 * @Author liangxp
 * @Date 2020/6/25 23:00
 **/
public class T08_Semaphore {
    volatile List list = new ArrayList();

    public void add(Object obj){
        list.add(obj);
    }

    public int getSize(){
        return list.size();
    }

    static Thread t1 = null;
    static Thread t2 = null;
    public static void main(String[] args) {
        T08_Semaphore c = new T08_Semaphore();
        Semaphore semaphore = new Semaphore(1);
        Thread t1= null;
        Thread t2 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("t2 结束");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");

        t1 = new Thread(() -> {
            System.out.println("t1 启动");
            try {
                semaphore.acquire();
                for (int i = 0; i < 5; i++) {
                    c.add(new Object());
                    System.out.println("add " + i);
                }
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                t2.start();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                semaphore.acquire();
                for (int i = 5; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add " + i);
                }
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"t1");

        t1.start();
    }

}
