package sort;

/**
 * @ClassName MergeSort
 * @Description 归并排序，采用分治法
 *      如果初始序列含有n个记录，先将总记录拆分成相同长度两个子序列，然后再对两个子序列继续拆分（利用了递归），
 *      最终拆分成n个有序的子序列，每个子序列的长度为1；然后两两归并，得到|n/2|（|x|表示不小于x的最小整数）个长度为2或1的有序子序列；
 *      再两两归并，如此重复（利用了递归），直至得到一个长度为n的有序序列为止，这种排序方法又被称为二路归并排序。
 * @Author liangxp
 * @Date 2020/6/27 17:06
 **/
public class MergeSort {
    public static void main(String[] args) {
        int arr [] = {32,43,23,13,5,26,78,49,100};
        mergeSort(arr,0,arr.length -1);

        for (int i : arr) {
            System.out.print(i + "  ");
        }
    }

    /**
     * 归并排序
     * 	速度仅次于快排，内存少的时候使用，可以进行并行计算的时候使用。
     选择相邻两个数组成一个有序序列。
     选择相邻的两个有序序列组成一个有序序列。
     重复第二步，直到全部组成一个有序序列。
     */
    public static void mergeSort(int[] numbers, int left, int right) {
        int t = 1;
        // 每组元素个数
        int size = right - left + 1;
        while (t < size) {
            int s = t;
            // 本次循环每组元素个数
            t = 2 * s;
            int i = left;
            while (i + (t - 1) < size) {
                merge(numbers, i, i + (s - 1), i + (t - 1));
                i += t;
            }
            if (i + (s - 1) < right)
                merge(numbers, i, i + (s - 1), right);
        }
    }

    private static void merge(int[] data, int p, int q, int r) {
        int[] B = new int[data.length];
        int s = p;
        int t = q + 1;
        int k = p;
        while (s <= q && t <= r) {
            if (data[s] <= data[t]) {
                B[k] = data[s];
                s++;
            } else {
                B[k] = data[t];
                t++;
            }
            k++;
        }
        if (s == q + 1)
            B[k++] = data[t++];
        else
            B[k++] = data[s++];
        for (int i = p; i <= r; i++)
            data[i] = B[i];
    }
}
