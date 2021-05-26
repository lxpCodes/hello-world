package arith;

import java.util.Arrays;
import java.util.Collections;

/**
 * @ClassName ReserseArr
 * @Description 反转数组
 * @Author liangxp
 * @Date 2021/5/10 10:12
 **/
public class ReserseArr {

    public static void main(String[] args) {
        Integer [] arr = {10, 20, 30, 40, 50};
        Collections.reverse(Arrays.asList(arr));
        System.out.println(Arrays.asList(arr));
        int n = arr.length;
        for (int i = 0; i < n / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[n - i -1];
            arr[n-i-1] = temp;
        }
        System.out.println(Arrays.asList(arr));


    }
}
