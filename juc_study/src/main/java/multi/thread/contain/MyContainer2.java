package multi.thread.contain;
import	java.util.concurrent.locks.Condition;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName MyContainer1
 * @Description TODO
 * @Author liangxp
 * @Date 2020/6/27 9:08
 **/
public class MyContainer2<T> {
    final private LinkedList linkedList = new LinkedList();
    final private int MAX = 10;
    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t){
        try {
            lock.lock();
            while (linkedList.size() == MAX){
                producer.await();
            }
            linkedList.add(t);
            ++ count;
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get(){
        T t = null;
        try {
            lock.lock();
            while (linkedList.size() == 0){
                consumer.await();
            }
            t = (T) linkedList.removeFirst();
            count --;
            producer.signalAll();//通知生产者生产
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        MyContainer2<String> c = new MyContainer2<>();
        //消费者
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                for (int j = 0; j < 5; j++) {
                    System.out.println(c.get());
                }
            },"consumer" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //生产者
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName() + " " + j);
                }
            },"provider" + i).start();
        }
    }
}
