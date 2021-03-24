package sort;

import java.math.BigDecimal;

/**
 * @ClassName MergeSort3
 * @Description TODO
 * @Author liangxp
 * @Date 2020/10/19 14:57
 **/
public class MergeSort3 {

    public static void main(String[] args) {
        int[] arr = new int[]{2,3,5,1};
        sort(arr, 0, arr.length-1);
        for (int i = 0;i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println("SDDD&".replace("&",""));

        System.out.println( new BigDecimal(180000).divide(new BigDecimal(50000),BigDecimal.ROUND_DOWN));
    }

    private static int[] sort(int[] arr, int low, int high) {
        int middle = (low + high)/2;
        if (low < high) {
            sort(arr, low, middle);
            sort(arr, middle+1, high);
            merge(arr, low, middle, high);
        }

        return arr;
    }

    private static void merge(int[] arr, int low, int middle, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = middle + 1;
        int k = 0;

        //先把较小的数移动到新的数组中
        while (i <= middle && j <= high) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }

        }

        //将左边剩下的移入数组
        while (i <= middle) {
            temp[k++] = arr[i++];
        }

        //将右边剩下的移入数组
        while (j <= high) {
            temp[k++] = arr[j++];
        }

        //将新数组覆盖原数组
        for (int x = 0;x < temp.length; x++) {
            arr[low + x] = temp[x];
        }

    }
}
