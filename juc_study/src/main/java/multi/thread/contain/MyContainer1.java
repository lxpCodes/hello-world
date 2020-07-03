package multi.thread.contain;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyContainer1
 * @Description TODO
 * @Author liangxp
 * @Date 2020/6/27 9:08
 **/
public class MyContainer1<T> {
    final private LinkedList linkedList = new LinkedList();
    final private int MAX = 10;
    private int count;

    public synchronized void put(T t){
        while (linkedList.size() == MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        linkedList.add(t);
        ++ count;
        this.notifyAll();//通知消费者消费
    }

    public synchronized T get(){
        T t = null;
        while (linkedList.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = (T) linkedList.removeFirst();
        count --;
        this.notifyAll();//通知生产者生产
        return t;
    }

    public static void main(String[] args) {
        MyContainer1<String> c = new MyContainer1<>();
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
