package leetcode;

import java.util.Arrays;

/**
 * @ClassName Case0088_merge_arr
 * @Description 合并两个有序数组
 * @Author liangxp
 * @Date 2021/4/6 11:00
 **/
public class Case0088_merge_arr {
    public static void main(String[] args) {
        int[] nums1 = {1, 3, 4, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        // 逆向双指针法 时间复杂度O(m+n) 空间复杂度O(1)
        merge(nums1, 3, nums2, nums2.length);
        // 双指针法 时间复杂度O(m+n) 空间复杂度O(m+n)
        merge2(nums1, 3, nums2, nums2.length);
    }


    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        int tail = m + n - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
        System.out.println(Arrays.toString(nums1));
    }

    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0;
        int[] sorted = new int[m + n];
        int cur;
        while (p1 < m || p2 < n) {
            if (p1 == m) {
                cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }
            sorted[p1 + p2 - 1] = cur;
        }
        for (int i = 0; i != m + n; ++i) {
            nums1[i] = sorted[i];
        }
        System.out.println(Arrays.toString(nums1));
    }

}

