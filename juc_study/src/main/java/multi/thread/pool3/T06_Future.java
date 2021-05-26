package multi.thread.pool3;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName T06_Future
 * @Description 测试Future
 * @Author liangxp
 * @Date 2021/4/27 10:59
 **/
public class T06_Future {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask(() ->{
            TimeUnit.MILLISECONDS.sleep(3000);
            return 100;
        });

        new Thread(task).start();
        System.out.println(task.get());

    }
}
