package multi.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName BoundedQueue
 * @Description 演示Condition的使用
 * @Author liangxp
 * @Date 2021/3/3 10:42
 **/
public class BoundedQueue<T> {
    private Object[] items;
    // 添加数组的下标 删除的下标和数组当前数量
    private int addIndex, removeIndex, count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public BoundedQueue(int size){
        items = new Object[size];
    }

    // 添加一个元素，如果数组满，则添加线程进入等待状态，直到有空位
    public void add(T t) throws InterruptedException {
        lock.lock();
        try{
            while (count == items.length){
                System.out.println("数组满了,添加线程等待");
                notFull.await();
            }
            items[addIndex] = t;
            if (++addIndex == items.length){
                addIndex = 0;
            }
            System.out.println("通知删除线程");
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }


    //从头部删除一个元素,如果数组空则删除线程进入等待状态,直到有新元素添加
    public T remove() throws InterruptedException{
        lock.lock();
        try{
            while (count == 0){
                System.out.println("数组空了，通知删除线程等待");
                notEmpty.await();
            }
            Object x = items[removeIndex];
            if (++removeIndex == items.length){
                removeIndex = 0;
            }
            --count;
            System.out.println("通知添加线程");
            notFull.signal();
            return (T)x;
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        BoundedQueue<Integer> boundedQueue = new BoundedQueue<>(5);

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread() {
                public void run() {
                    try {
                        boundedQueue.add(new Integer(10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }

        for (int i = 0; i < 10; i++) {
            Thread thread2 = new Thread(){
                public void run(){
                    try {
                        System.out.println("删除:" + boundedQueue.remove());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread2.start();
        }
    }


}
