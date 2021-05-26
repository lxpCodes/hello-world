package multi.thread.pool2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName T09_Lock_condition
 * @Description ReentrantLock实现交替打印
 * @Author liangxp
 * @Date 2021/4/23 14:26
 **/
public class T09_Lock_condition {
    public static void main(String[] args) {
        char[] c1 = "1234567".toCharArray();
        char[] c2 = "ABCDEFG".toCharArray();

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            try {
                lock.lock();
                for (char c : c1) {
                    System.out.print(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t1").start();

        new Thread(() ->{
            try {
                lock.lock();
                for (char c : c2) {
                    System.out.print(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t2").start();

    }
}
