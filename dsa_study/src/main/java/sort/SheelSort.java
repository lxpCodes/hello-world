package sort;

/**
 * @ClassName SheelSort
 * @Description 希尔排序-不稳定排序
 * 分组的插入排序
 * @Author liangxp
 * @Date 2020/6/27 16:57
 **/
public class SheelSort {
    public static void main(String[] args) {
        int arr [] = {32,43,23,13,5,26,78,49,100};
        /**
         * 希尔排序
         * 对于直接插入排序问题，数据量巨大时
         * 将数的个数设为n，取奇数k=n/2，将下标差值为k的书分为一组，构成有序序列。
         * 再取k=k/2 ，将下标差值为k的书分为一组，构成有序序列。
         * 重复第二步，直到k=1执行简单插入排序。
         *
         * 首先确定分的组数。
         * 然后对组中元素进行插入排序。
         * 然后将length/2，重复1,2步，直到length=0为止。
         * @param arr
         */
        int d = arr.length;
        while (d != 0) {
            d = d/2;
            for (int x = 0; x < d; x++) {
                //分的组数
                for (int i = x + d; i < arr.length; i += d) {
                    //组中的元素，从第二个数开始
                    int j  = i - d;
                    //j为有序列的最后一位的位数
                    int tmp = arr[i];
                    //要插入的元素
                    for (; j >= 0 && tmp < arr[j]; j -= d) {
                        arr[j+d] = arr[j];
                        //向后移动d位
                    }
                    arr[j + d] = tmp;
                    System.out.print("分组:d=" + d +"循环:i="+i+"后数组为 ");
                    for (int k: arr) {
                        System.out.print(k+"  ");
                    }
                    System.out.println();
                }
            }
        }

        for (int i : arr) {
            System.out.print(i + "  ");
        }
    }
}
