package arith;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName FindDuplicate
 * @Description 查找重复数字
 * @Author liangxp
 * @Date 2020/6/27 21:44
 **/
public class FindDuplicate {
    public static void main(String[] args) {
        int arr[] = {1,3,4,4,2};
//        int resp = find1(arr);
//        int resp = find2(arr);
        int resp = find3(arr);
        System.out.println(resp);

    }

    public static int find1(int[] arr){
        Map map = new HashMap<String,String>();
        for (int i = 0; i < arr.length; i++){
            if (map.put(""+arr[i],""+arr[i]) != null) {
                return arr[i];
            }
        }
        return -1;
    }

    public static int find2(int[] nums){
        int n = nums.length - 1;
        int low = 1;
        int high = n;
        int mid;
        while (low < high) {
            mid = (low + high) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= mid)
                    count++;
            }
            if (count > mid)
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }

    //因为元素的大小是从1到n，有一个是重复的，所以我们可以通过下标和值映射成一个环
    public static int find3(int[] nums) {
       if (nums.length > 1) {
           int slow = nums[0];
           int fast = nums[nums[0]];
            while (slow != fast) {
               slow = nums[slow];
               fast = nums[nums[fast]];
            }
            fast = 0;
            while (fast != slow) {
                fast = nums[fast];
                slow = nums[slow];
            }
            return slow;
        }
       return -1;
    }
}
