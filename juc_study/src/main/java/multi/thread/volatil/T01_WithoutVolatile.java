package multi.thread.volatil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName T01_WithoutVolatile
 * @Description TODO
 * @Author liangxp
 * @Date 2020/6/25 23:00
 **/
public class T01_WithoutVolatile {
    List list = new ArrayList();

    public void add(Object obj){
        list.add(obj);
    }

    public int getSize(){
        return list.size();
    }

    public static void main(String[] args) {
        T01_WithoutVolatile c = new T01_WithoutVolatile();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(() -> {
            while (true){
                if (c.getSize() == 5){
                    break;
                }
            }
            System.out.println("t2 结束");
        }, "t2").start();

    }

}
