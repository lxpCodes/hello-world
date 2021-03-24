package lagou;

/**
 * @ClassName Case07_RemoveDup
 * @Description 数组去重
 * @Author liangxp
 * @Date 2020/9/30 14:31
 **/
public class Case07_RemoveDup {
    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int temp = nums[0];
        int len = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != temp){
                nums[len] = nums[i];
                temp = nums[i];
                len ++;
            }
        }

        System.out.println(len);
        for (int i = 0; i < len; i++) {
            System.out.println(nums[i]);
        }
    }
}
