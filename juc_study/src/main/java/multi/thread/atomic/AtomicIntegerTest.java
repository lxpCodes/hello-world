package multi.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName AtomicIntegerTest
 * @Description 测试原子操作类
 * @Author liangxp
 * @Date 2020/6/16 17:35
 **/
public class AtomicIntegerTest {
    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {

        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.incrementAndGet());
        atomicInteger.set(999);
        System.out.println(atomicInteger.get());

        int expect = 123;
        int newvalue = 124;
        boolean resp = atomicInteger.compareAndSet(expect, newvalue);
        System.out.println(resp);
        System.out.println(atomicInteger.get());

        atomicInteger.set(123);
        resp = atomicInteger.compareAndSet(expect, newvalue);
        System.out.println(resp);
        System.out.println(atomicInteger.get());

        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.incrementAndGet());
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.get());
    }
}
