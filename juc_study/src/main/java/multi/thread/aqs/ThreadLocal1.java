package multi.thread.aqs;
import	java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadLocal1
 * @Description TODO
 * @Author liangxp
 * @Date 2020/6/27 11:09
 **/
public class ThreadLocal1 {
//    volatile static Person p = new Person();

    static ThreadLocal<Person> t1 = new ThreadLocal<>();
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(t1.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            t1.set(new Person());
        }).start();
    }

}

class Person {
    String name = "zhansgan";
}