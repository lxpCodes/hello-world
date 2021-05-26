package multi.thread.cow;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName T05_LinkedBlockingQueue
 * @Description TODO
 * @Author liangxp
 * @Date 2021/4/22 18:03
 **/
public class T05_LinkedBlockingQueue {

    static BlockingQueue<String> strs = new LinkedBlockingQueue<>();
    static Random r = new Random();

    public static void main(String[] args) {
        new Thread(() ->{
            for (int i = 0; i < 100; i++) {
                try {
                    // 满了就会等待
                    strs.put("a" + i);
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"p1").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() ->{
                for (;;){
                    try {
                        // 空了就会等待
                        System.out.println(Thread.currentThread().getName() + "take - " + strs.take());//队列空了会等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"c" + i).start();

        }
    }
}
