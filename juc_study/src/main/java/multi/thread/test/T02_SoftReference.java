package multi.thread.test;

import java.lang.ref.SoftReference;

/**
 * @ClassName T02_SoftReference
 * @Description 软引用演示，设置堆内存30M 第三次打印就已经为null，设置堆内存50m，第三次打印未被回收
 *              -Xms50m -Xmx50m
 * @Author liangxp
 * @Date 2020/6/28 16:35
 **/
public class T02_SoftReference {
    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte [1024*1024*10]);
        System.out.println(m.get());
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());
        byte[] b = new byte [1024*1024*15];
        System.out.println(m.get());

    }
}
