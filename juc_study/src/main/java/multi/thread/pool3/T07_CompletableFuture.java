package multi.thread.pool3;


import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName T07_CompletableFuture
 * @Description 测试CompletableFuture
 * @Author liangxp
 * @Date 2021/4/27 11:03
 **/
public class T07_CompletableFuture {
    public static void main(String[] args) throws Exception {
        long start,end;
        start = System.currentTimeMillis();

       /* priceOfTM();
        priceOfTB();
        priceOfJD();

        end = System.currentTimeMillis();
        System.out.println("cost time :" + (end - start));*/

        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(() ->priceOfTM());
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(() ->priceOfTB());
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(() ->priceOfJD());

        CompletableFuture.allOf(futureTB,futureTM,futureJD).join();

        CompletableFuture.supplyAsync(() -> priceOfTM())
                .thenApply(String::valueOf)
                .thenApply(str -> "price" + str)
                .thenAccept(System.out::println);

        end = System.currentTimeMillis();

        System.out.println("cost time" + (end - start));
        System.in.read();

    }

    private static double priceOfJD() {
        delay();
        return 3.0;
    }

    private static double priceOfTB() {
        delay();
        return 2.0;
    }

    private static double priceOfTM() {
        delay();
        return 1.0;
    }

    private static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sleep :" + time);
    }
}
