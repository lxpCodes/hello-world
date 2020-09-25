package multi.thread.disruptor;

/**
 * @ClassName FalseSharing
 * @Description 测试 缓存行的伪共享问题
 * @Author liangxp
 * @Date 2020/9/3 10:56
 **/
public class FalseSharing implements Runnable{
    public final static long ITERATIONS = 500L * 1000L * 100L;
    private int arrayIndex = 0;

    private static ValueNoPadding[] longs;
    public FalseSharing(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception {
        for(int i=1;i<10;i++){
            System.gc();
            final long start = System.currentTimeMillis();
            runTest(i);
            System.out.println("Thread num "+i+" duration = " + (System.currentTimeMillis() - start));
        }

    }

    // 使用了共享机制打印结果
    /*Thread num 1 duration = 592
    Thread num 2 duration = 752
    Thread num 3 duration = 724
    Thread num 4 duration = 883
    Thread num 5 duration = 1597
    Thread num 6 duration = 1205
    Thread num 7 duration = 1452
    Thread num 8 duration = 1320
    Thread num 9 duration = 1400*/


    // 将代码中ValuePadding都替换为ValueNoPadding后的打印结果
    /*Thread num 1 duration = 671
    Thread num 2 duration = 2605
    Thread num 3 duration = 2630
    Thread num 4 duration = 2685
    Thread num 5 duration = 4996
    Thread num 6 duration = 5389
    Thread num 7 duration = 4857
    Thread num 8 duration = 4912
    Thread num 9 duration = 3918*/

    private static void runTest(int NUM_THREADS) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        longs = new ValueNoPadding[NUM_THREADS];
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new ValueNoPadding();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = 0L;
        }
    }

    public final static class ValuePadding {
        protected long p1, p2, p3, p4, p5, p6, p7;
        protected volatile long value = 0L;
        protected long p9, p10, p11, p12, p13, p14;
        protected long p15;
    }
    public final static class ValueNoPadding {
        // protected long p1, p2, p3, p4, p5, p6, p7;
        protected volatile long value = 0L;
        // protected long p9, p10, p11, p12, p13, p14, p15;
    }
}
