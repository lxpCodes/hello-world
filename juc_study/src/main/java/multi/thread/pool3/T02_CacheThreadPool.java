package multi.thread.pool3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName T02_CacheThreadPool
 * @Description CacheThreadPool
 * @Author liangxp
 * @Date 2021/4/27 11:28
 **/
public class T02_CacheThreadPool {
    public static void main(String[] args) throws Exception{
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);
        for (int i = 0; i < 200; i++) {
            service.execute(() ->{
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                System.out.println(service);
            });

        }
        System.out.println(service);
        TimeUnit.SECONDS.sleep(8);
        System.out.println("end:"+service);

    }
}
