package multi.thread.disruptor;

/**
 * @ClassName CacheLineEffect
 * @Description 测试缓存行使用与否的效果对比
 * @Author liangxp
 * @Date 2020/9/3 10:21
 **/
public class CacheLineEffect {
    //考虑一般缓存行大小是64字节，一个 long 类型占8字节
    static  long[][] arr;

    public static void main(String[] args) {
        arr = new long[1024 * 1024][];
        for (int i = 0; i < 1024 * 1024; i++) {
            arr[i] = new long[8];
            for (int j = 0; j < 8; j++) {
                arr[i][j] = 1L;
            }
        }
        long sum1 = 0L;
        long marked = System.currentTimeMillis();
        for (int i = 0; i < 1024 * 1024; i+=1) {
            for(int j =0; j< 8;j++){
                sum1 += arr[i][j];
            }
        }
        System.out.println("use cacheline Loop times:" + (System.currentTimeMillis() - marked) + "ms, sum = " + sum1);
        long sum2 = 0L;
        marked = System.currentTimeMillis();
        for (int i = 0; i < 8; i+=1) {
            for(int j =0; j< 1024 * 1024;j++){
                sum2 += arr[j][i];
            }
        }
        System.out.println("no use cacheline Loop times:" + (System.currentTimeMillis() - marked) + "ms, sum = " + sum2);
    }
}
