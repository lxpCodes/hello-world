package multi.thread.pool3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName T01_SingleThreadPool
 * @Description TODO
 * @Author liangxp
 * @Date 2021/4/27 11:23
 **/
public class T01_SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            final int j = i;
            executorService.execute(() ->{
                System.out.println(j + " " + Thread.currentThread().getName());
            });

        }
    }
}
