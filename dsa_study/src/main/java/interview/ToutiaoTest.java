package interview;

import java.util.Arrays;

/**
 * @ClassName interview.ToutiaoTest
 * @Description 一个整形数组A，一个整数b，把A中3个数相加等于b的组合全部打印出来
 * @Author liangxp
 * @Date 2020/10/15 20:06
 **/
public class ToutiaoTest {
    public static void main(String[] args) {
        int[] arr = {1,3,4,5,6,9};
        int target = 13;
        for (int i = 0;i < arr.length; i++){
            int temp = target - arr[i];
            int k = i + 1;
            for(int j = i + 2; j < arr.length;j++){
                if(arr[k]+ arr[j] == temp){
                    System.out.println(Arrays.asList(arr[i],arr[k],arr[j]));
                }
            }
        }
    }
}
