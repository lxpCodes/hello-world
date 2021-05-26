package multi.thread.pool3;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName T05_HelloThreadPool
 * @Description TODO
 * @Author liangxp
 * @Date 2021/4/27 11:14
 **/
public class T05_HelloThreadPool {

    static class Task implements Runnable{
        private int i;

        public Task(int i){
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Task " + i);
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    '}';
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2,4,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 8; i++) {
            tpe.execute(new Task(i));
        }

        System.out.println("queue:" + tpe.getQueue());
        tpe.execute(new Task(100));
        System.out.println(tpe.getQueue());
        tpe.shutdown();
    }
}
