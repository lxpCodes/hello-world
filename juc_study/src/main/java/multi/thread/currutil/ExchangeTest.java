package multi.thread.currutil;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ExchangeTest
 * @Description Exchange 测试
 * @Author liangxp
 * @Date 2020/6/17 16:31
 **/
public class ExchangeTest {

    private static final Exchanger<String> exchanger = new Exchanger();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String A = "银行流水A";
                try {
                    exchanger.exchange(A);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String B = "银行流水A";
                try {
                    String A = exchanger.exchange(B);
                    System.out.println("A与B数据是否一致: " + B.equals(A) + " A:" + A + "B:" + B);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        threadPool.shutdown();

    }
}
