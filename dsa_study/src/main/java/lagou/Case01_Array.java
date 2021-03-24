package lagou;

import java.util.Arrays;


/**
 * @ClassName Case01_Array
 * @Description 拉勾案例-反转数组 找出最大值 找出出现次数最多的数
 * @Author liangxp
 * @Date 2020/9/29 9:34
 **/
public class Case01_Array {
    public static void main(String[] args) {
        int a[] = { 1, 2, 3, 4, 5 };
        reverseArr(a);
        reverseArr2(a);
        findMax(a);
        findMostTimes();

    }

    // 反转数组
    public static void reverseArr(int[] a){
        int b[] = new int[5];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        for (int i = 0; i < a.length; i++) {
            b[a.length - i - 1] = a[i];
        }
        System.out.println(Arrays.toString(b));
    }

    // 反转数组
    public static void reverseArr2(int[] a){
        int tmp = 0;
        for (int i = 0; i < (a.length / 2); i++) {
            tmp = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = tmp;
        }
        System.out.println(Arrays.toString(a));
    }

    // 查询数组中最大值
    public static void findMax(int[] a){
        int max_val = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max_val) {
                max_val = a[i];
            }
        }
        System.out.println(max_val);
    }

    // 查找出一个数组中，出现次数最多的那个元素的数值。
    public static void findMostTimes(){
        int a[] = { 1, 3, 4, 3, 4, 1, 3 };
        int val_max = -1;
        int time_max = 0;
        int time_tmp = 0;
        for (int i = 0; i < a.length; i++) {
            time_tmp = 0;
            for (int j = 0; j < a.length; j++) {
                if (a[i] == a[j]) {
                    time_tmp += 1;
                }
                if (time_tmp > time_max) {
                    time_max = time_tmp;
                    val_max = a[i];
                }
            }
        }
        System.out.println(val_max);
    }


}
