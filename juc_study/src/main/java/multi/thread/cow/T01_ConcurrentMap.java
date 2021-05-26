package multi.thread.cow;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName T01_ConcurrentMap
 * @Description TODO
 * @Author liangxp
 * @Date 2021/4/22 14:46
 **/
public class T01_ConcurrentMap {
    public static void main(String[] args) {
//        Map<String,String> map = new ConcurrentHashMap<>();     //312 30014
//        Map<String,String> map = new ConcurrentSkipListMap<>(); //283 79985
//        Map<String,String> map = new Hashtable<>();               //113 11463
//        Map<String,String> map = Collections.synchronizedMap(new HashMap<>()); //159 31115
        Map<String,String> map = new TreeMap<>();

        Random r = new Random();
        Thread[] ths = new Thread[100];
        CountDownLatch latch = new CountDownLatch(ths.length);

        long start = System.currentTimeMillis();

        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    map.put("a" + r.nextInt(100000),"a" + r.nextInt(100000));
                    latch.countDown();
                }
            });
        }

        Arrays.asList(ths).forEach(t -> t.start());

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(map.size());
    }
}
