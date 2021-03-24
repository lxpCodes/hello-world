package multi.thread.lock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName FairAndUnfairTest
 * @Description 公平锁与非公平锁测试
 * @Author liangxp
 * @Date 2021/3/2 16:35
 **/
public class FairAndUnfairTest {
    private static Lock fairLock = new ReentrantLock2(true);
    private static Lock unfairLock = new ReentrantLock2(false);


    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            /*Thread thread = new Thread(new Job(fairLock)){
                public String toString(){
                    return getName();
                }
            };
            thread.setName("fair-" + i);
            thread.start();*/

            Thread thread2 = new Thread(new Job(unfairLock)){
                public String toString(){
                    return getName();
                }
            };
            thread2.setName("nonfair-" + i);
            thread2.start();
        }
    }


    private static class Job extends Thread{
        private Lock lock;
        public Job(Lock lock){
            this.lock = lock;
        }

        public void run(){
            for (int i = 0; i < 2; i++) {
                lock.lock();
                try {
                    Thread.sleep(1000);
                    System.out.println("locked by:" + Thread.currentThread().getName() + ", Waiting by" + ((ReentrantLock2)lock).getQueuedThreads());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private static class ReentrantLock2 extends ReentrantLock{
        public ReentrantLock2(boolean fair){
            super(fair);
        }

        public Collection<Thread> getQueuedThreads(){
            List<Thread> list = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(list);
            return list;
        }
    }
}
