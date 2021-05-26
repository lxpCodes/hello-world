package multi.thread.pool3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName T11_ParallelStreamAPI
 * @Description 并行流
 * @Author liangxp
 * @Date 2021/4/27 16:50
 **/
public class T11_ParallelStreamAPI {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 10000; i++) {
            nums.add(1000000 + r.nextInt(1000000));
        }
        System.out.println(nums);

        long start = System.currentTimeMillis();
        nums.forEach(v -> isPrime(v));
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        System.out.println("---------");

        start = System.currentTimeMillis();
        nums.parallelStream().forEach(T11_ParallelStreamAPI::isPrime);
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static boolean isPrime(int  num) {
        for (int i = 2; i <= num/2; i++) {
            if (num % i == 0){
                return false;
            }
        }
        return true;
    }
}
