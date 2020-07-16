package arith;

/**
 * @ClassName DeleteRepetElement
 * @Description 删除排序数组的重复项
 *              给定一个排序数组，原地删除重复出现的元素，使得每个元素出现一次，返回移除后的数组和长度，不需要考虑数组中超出新长度后面的元素
 *              要求：空间复杂度O（1）,即不使用额外的数组空间
 * @Author liangxp
 * @Date 2020/7/16 10:34
 **/
public class DeleteRepetElement {
    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int temp = nums[0];
        int len = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != temp) {
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
