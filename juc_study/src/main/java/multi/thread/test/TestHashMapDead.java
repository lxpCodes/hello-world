package multi.thread.test;

import java.util.HashMap;
import java.util.UUID;

/**
 * @ClassName TestHashMapDead
 * @Description 测试HashMap的死循环
 * @Author liangxp
 * @Date 2021/3/3 15:34
 **/
public class TestHashMapDead {
    public static void main(String[] args) throws InterruptedException {
        final HashMap<String,String> map = new HashMap<>(2);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(),"");
                        }
                    },"ftf" + i).start();
                }

            }
        },"ftf");
        t.start();
        t.join();

    }
}
