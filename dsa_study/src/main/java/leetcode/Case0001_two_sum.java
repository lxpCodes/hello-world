package leetcode;

import java.util.*;

/**
 * @ClassName Case0001_two_sum
 * @Description 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *              给定 nums = [2, 7, 11, 15], target = 9 因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1]
 * @Author liangxp
 * @Date 2020/12/30 18:00
 **/
public class Case0001_two_sum {
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
