package leetcode;

import java.util.Arrays;

/**
 * @ClassName Case0088_merge_arr_lx
 * @Description 练习逆向双指针法合并数组
 * @Author liangxp
 * @Date 2021/4/6 14:56
 **/
public class Case0088_merge_arr_lx {
    public static void main(String[] args) {
        int[] nums1 = {1, 3, 4, 0 , 0, 0};
        int[] nums2 = {2, 5, 6};
        merge(nums1,3,nums2,3);
    }

    public static void merge(int[] nums1,int m, int[] nums2,int n){
        int p1 = m -1;
        int p2 = n -1;
        int tail = m + n - 1;
        int cur;
        while (p1 >=0 || p2 >= 0){
            if (p1 == -1){
                cur = nums2[p2--];
            } else if (p2 == -1){
                cur = nums1[p1--];
            } else if(nums1[p1] > nums2[p2]){
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
        System.out.println(Arrays.toString(nums1));
    }
}
