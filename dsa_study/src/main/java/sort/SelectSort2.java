package sort;

import java.util.Arrays;

/**
 * @ClassName SelectSort2
 * @Description 选择排序2
 * @Author liangxp
 * @Date 2020/10/15 11:15
 **/
public class SelectSort2 {
    public static void main(String[] args) {
        int arr [] = {32,43,23,13,5,26,78,49,100};

        int[] result = selectionSort(arr);
        System.out.println(Arrays.toString(result));
    }

    private static int[] selectionSort(int[] arr) {
        if (arr.length ==0){
            return arr;
        }
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]){//找到最小的数
                    minIndex = j;//保存最小数的索引
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;

        }
        return arr;
    }

}
