package multi.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName GoodDateFormat
 * @Description SimpleDateFormat多线程下的正确用法
 * @Author liangxp
 * @Date 2020/8/21 16:18
 **/
public class GoodDateFormat {

    ThreadLocal<SimpleDateFormat> format = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static void main(String[] args) {
        final GoodDateFormat goodDateFormat = new GoodDateFormat();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executor.submit(() -> {
                try {
                    System.out.println(goodDateFormat.format.get().parse("2020-08-21 16:17:45"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
    }


}
