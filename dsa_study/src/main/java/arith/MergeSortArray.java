package arith;

import java.util.Arrays;

/**
 * @ClassName MergeSortArray
 * @Description 合并两个有序数组，合并后数组也是升序，时间复杂度O(n)
 * @Author liangxp
 * @Date 2020/7/16 14:17
 **/
public class MergeSortArray {
    public static void main(String[] args) {
        int[] arr1 = {2,4,7,9,10,22,45};
        int[] arr2 = {3,6,7,8,16,28,33,43,50};
//        mergeArray(arr1,arr2);
        mergeArray2(arr1,arr2);
        int d = 0xFFFFFFFF;
        char c = '\u0571';
        byte b = 01;
        int i = 'a';
        long l = 455555666666L;
        System.out.println(d);
        System.out.println(c);
        System.out.println(b);
        System.out.println(i);
        System.out.println(l);
    }

    private static void mergeArray2(int[] arr1, int[] arr2) {
        int[] temp = new int[arr1.length + arr2.length];
        int index_1 = 0;
        int index_2 = 0;
        for (int i = 0; i < temp.length; i++) {
            if (index_1 < arr1.length && arr1[index_1] < arr2[index_2]) {
                temp[i] = arr1[index_1];
                index_1 ++;
            } else {
                temp[i] = arr2[index_2];
                index_2 ++;
            }
        }
        System.out.println(Arrays.toString(temp));
    }

    private static void mergeArray(int[] arr1, int[] arr2) {
        int len1 = 0;
        int len2 = 0;
        int[] arr3;
        int[] arr4;
        if (arr1.length < arr2.length) {
            len1 = arr1.length;
            len2 = arr2.length;
            arr3 = arr2;
            arr4 = arr1;
        } else {
            len1 = arr2.length;
            len2 = arr1.length;
            arr3 = arr1;
            arr4 = arr2;
        }
        int[] temp = new int[len1 + len2];
        for (int i = 0; i < len1; i++) {
            if (arr1[i] < arr2[i]) {
                temp[2*i] = arr1[i];
                temp[2*i+1] = arr2[i];
            } else {
                temp[2*i] = arr2[i];
                temp[2*i+1] = arr1[i];
            }
        }
        int j = 2*len1;
        for (int i = len1; i < len2; i++) {
            if (arr4[len1-1] < arr3[i]) {
                temp[j++] = arr3[i];
            } else {
                temp[2*len1-1] = arr3[i];
                temp[j++] = arr4[len1-1];
            }
        }
        System.out.println(Arrays.toString(temp));

    }
}
