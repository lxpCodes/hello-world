package multi.thread.cow;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName T06_ArrayBlockingQueue
 * @Description 测试ArrayBlockingQueue
 * @Author liangxp
 * @Date 2021/4/23 10:39
 **/
public class T06_ArrayBlockingQueue {

    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.put("a" + i);
        }

//        strs.put("aaa");
//        strs.add("aaa");
//        strs.offer("aaa");
        strs.offer("aaa",5, TimeUnit.SECONDS);

        System.out.println(strs);


    }
}
