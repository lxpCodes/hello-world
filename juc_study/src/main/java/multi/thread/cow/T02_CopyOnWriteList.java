package multi.thread.cow;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName T02_CopyOnWriteList
 * @Description 写时复制List
 * @Author liangxp
 * @Date 2021/4/22 17:44
 **/
public class T02_CopyOnWriteList {
    public static void main(String[] args) {
//        List<String> lists = new ArrayList<>(); // 会报错
        List<String> lists = Collections.synchronizedList(new ArrayList<>());//181 100000
//        List<String> lists = new Vector<>();    // 236 100000
//        List<String> lists = new CopyOnWriteArrayList<>();    //8007 100000
        // 效率依次降低

        Random r = new Random();
        Thread[] ths = new Thread[100];
        for (int i = 0; i < ths.length; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        lists.add("a" + r.nextInt(10000));
                    }
                }
            };
            ths[i] = new Thread(task);
        }

        runAndComputeTime(ths);
        System.out.println(lists.size());
    }

    private static void runAndComputeTime(Thread[] ths) {
        long start = System.currentTimeMillis();
        Arrays.asList(ths).forEach(t -> t.start());
        Arrays.asList(ths).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
