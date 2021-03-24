package lagou;

import java.util.*;

/**
 * @ClassName Case10_FindTargetNum
 * @Description 给定一个整数数组 arr 和一个目标值 target，请你在该数组中找出加和等于目标值的那两个整数，并返回它们的在数组中下标
 * @Author liangxp
 * @Date 2020/9/30 15:12
 **/
public class Case10_FindTargetNum {
    public static void main(String[] args) {

        System.out.println(Arrays.toString(getTwo()));
    }

    public static int[] getTwo(){
        int arr[] = {1, 2, 3, 4, 5, 6};
        int target = 4;

        Map<Integer,Integer> map = new HashMap();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i],i);
        }

        for (int i = 0; i < arr.length; i++) {
            int temp = target - arr[i];
            if (map.containsKey(temp) && map.get(temp) != i){
                return new int[]{ map.get(temp),i};
            }
        }
        return null;
    }
}
