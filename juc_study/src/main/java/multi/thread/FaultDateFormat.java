package multi.thread;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName FaultDateFormat
 * @Description SimpleDateFormat的错误使用
 * @Author liangxp
 * @Date 2020/8/21 16:07
 **/
public class FaultDateFormat {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        final FaultDateFormat faultDateFormat = new FaultDateFormat();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executor.submit(() -> {
                try {
                    System.out.println(faultDateFormat.format.parse("2020-08-21 16:17:45"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
    }

}
