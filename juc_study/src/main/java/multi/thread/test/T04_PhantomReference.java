package multi.thread.test;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import	java.lang.ref.ReferenceQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName T04_PhantomReference
 * @Description 虚引用演示
 * @Author liangxp
 * @Date 2020/6/28 16:54
 **/
public class T04_PhantomReference {
    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue<M> QUEUE = new ReferenceQueue<> ();

    public static void main(String[] args) {
        PhantomReference<M> phantomReference = new PhantomReference<>(new M(), QUEUE);

        new Thread(() -> {
            while (true){
                LIST.add(new byte[1024 * 1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                System.out.println(phantomReference.get());
            }
        }).start();

        new Thread(() -> {
            while (true){
                Reference<? extends M> poll = QUEUE.poll();
                if (poll != null){
                    System.out.println("虚引用对象被jvm回收了" + poll);
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
