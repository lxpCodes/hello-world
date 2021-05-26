package multi.thread.pool2;

import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName T02_LockSupport
 * @Description 两个线程交替打印
 * @Author liangxp
 * @Date 2021/4/23 11:16
 **/
public class T02_LockSupport {
    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        char[] c1 = "1234567".toCharArray();
        char[] c2 = "ABCDEFG".toCharArray();
        t1 = new Thread(() -> {
            for (char c : c1) {
                System.out.print(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        },"t1");

        t2 = new Thread(() -> {
            for (char c : c2) {
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);
            }
        },"t2");

        t1.start();
        t2.start();
    }

}
