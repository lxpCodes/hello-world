package multi.thread.volatil;
import	java.util.concurrent.locks.LockSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName T01_WithoutVolatile
 * @Description TODO
 * @Author liangxp
 * @Date 2020/6/25 23:00
 **/
public class T06_LockSupport {
    volatile List list = new ArrayList();

    public void add(Object obj){
        list.add(obj);
    }

    public int getSize(){
        return list.size();
    }

    public static void main(String[] args) {
        T06_LockSupport c = new T06_LockSupport();
        Thread t1= null;
        Thread finalT = t1;
        Thread t2 = new Thread(() -> {
            System.out.println("t2 启动");
            if (c.getSize() != 5) {
                LockSupport.park();
            }

            System.out.println("t2 结束");
            LockSupport.unpark(finalT);
        }, "t2");
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1 = new Thread(() -> {
            System.out.println("t1 启动");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);
                if (c.getSize() == 5){
                    LockSupport.unpark(t2);
                }
                //睡眠代码注掉就有问题
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1");
        t1.start();
    }

}
