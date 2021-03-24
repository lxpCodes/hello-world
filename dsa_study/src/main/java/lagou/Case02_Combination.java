package lagou;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Case02_Combination
 * @Description 案例2
 * @Author liangxp
 * @Date 2020/9/29 9:43
 **/
public class Case02_Combination {


    public static void main(String[] args) {
        findPoss();
        findPoss2();
        findMostTimes2();
    }

    // 假设有任意多张面额为 2 元、3 元、7 元的货币，现要用它们凑出 100 元，求总共有多少种可能性
    public static void findPoss(){
        int count = 0;
        for (int i = 0; i <= (100 / 7); i++) {
            for (int j = 0; j <= (100 / 3); j++) {
                /*for (int k = 0; k <= (100 / 2); k++) {
                    if (i * 7 + j * 3 + k * 2 == 100) {
                        count += 1;
                    }
                }*/
                // 改进
                if ((100 - i*7 - j*3 >= 0) && ((100- i*7 - j*3) % 2 == 0)) {
                    count += 1;
                }
            }
        }
        System.out.println(count);
    }

    public static void findPoss2(){
        int count = 0;
        for (int i = 0; i <= (100 / 7); i++) {
            for (int j = 0; j <= (100 - 7*i) / 3; j++) { //减少执行时间
                // 改进
                if ((100 - i*7 - j*3 >= 0) && ((100- i*7 - j*3) % 2 == 0)) {
                    count += 1;
                    int k = (100- i*7 - j*3)/2;
                    System.out.println(i + " " + j + " "+ k + "  "+  (i*7 + j *3 + k*2));
                }
            }
        }
        System.out.println(count);
    }

    // 查找出一个数组中，出现次数最多的那个元素的数值。
    public static void findMostTimes2(){
        int a[] = { 1, 2, 3, 4, 5, 5, 6 };
        Map<Integer, Integer> d = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            if (d.containsKey(a[i])) {
                d.put(a[i], d.get(a[i]) + 1);
            } else {
                d.put(a[i], 1);
            }
        }
        int val_max = -1;
        int time_max = 0;
        for (Integer key : d.keySet()) {
            if (d.get(key) > time_max) {
                time_max = d.get(key);
                val_max = key;
            }
        }
        System.out.println(val_max);
    }

}
