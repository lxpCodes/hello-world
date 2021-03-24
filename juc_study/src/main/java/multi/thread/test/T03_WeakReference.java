package multi.thread.test;

import java.lang.ref.WeakReference;

/**
 * @ClassName T03_WeakReference
 * @Description 虚引用演示
 * @Author liangxp
 * @Date 2020/6/28 16:46
 **/
public class T03_WeakReference {
    public static void main(String[] args) {
        WeakReference<M> w = new WeakReference<> (new M());
        System.out.println("first: " + w.get());
        System.gc();
        System.out.println("second: " + w.get());
        ThreadLocal<M> tl = new ThreadLocal<>();
        Thread thread = Thread.currentThread();
        System.out.println("thread hashcode: " + thread.hashCode());
        tl.set(new M());
        System.out.println(tl.get());;
        tl.remove();

    }
}
