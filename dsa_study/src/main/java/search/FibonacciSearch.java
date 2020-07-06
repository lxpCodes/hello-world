package search;

import java.util.Arrays;

/**
 * @ClassName FibonacciSearch
 * @Description 斐波那契查找 查找特点就是左侧半区范围大于右侧半区范围，对处于右侧当中的大部分数据，其工作效率要高一些
 *              尽管斐波那契查找的时间复杂也为O(logn)，但就平均性能来说，斐波那契查找要优于二分查找
 *              二分查找是进行加法与除法运算（mid=(low＋high)/2），
 *              插值查找进行复杂的四则运算（mid=low＋(highlow)*(key-a[low])/(a[high]-a[low])），
 *              而斐波那契查找只是最简单加减法运算（mid=low＋F[k-1]-1）
 * @Author liangxp
 * @Date 2020/7/6 16:06
 **/
public class FibonacciSearch {
    //斐波那契数组
    public static int[] fibonacci = new int[]{1, 1, 2, 3, 5, 8, 13};
    public static void main(String[] args) {
        int[] arr = new int[]{1, 16, 24, 35, 47, 59, 62, 73, 88, 99,109};


        System.out.println(fibonacciSearch(arr,99));
    }

    public static int fibonacciSearch(int[] arr,int key){
        int low = 0;
        int high = arr.length - 1;
        if (arr[low] > key || arr[high] < key) {
            return -1;
        }
        int k = 0, mid;
        //计算high位于斐波那契数列的位置
        while (high > fibonacci[k]){
            k++;
        }
        //扩展原数组，长度扩展为fibonacci[k]=13，即多加了三个位置
        arr = Arrays.copyOf(arr,fibonacci[k]);
        //为了保证数组的顺序，把扩展的值都设置为原数组的最大值
        for (int j = high + 1; j < arr.length; j++) {
            arr[j] = arr[high];
        }

        //开始斐波那契查找
        while(low <= high){
            //计算当前分隔的下标索引，
            mid = low + fibonacci[k-1] - 1;
            //若查找记录小于当前分隔记录
            if (key < arr[mid]){
                //最高下标调整到分隔下标mid-1
                high = mid -1;
                //斐波那契数列下标减一位
                k = k - 1;
            } else if(key > arr[mid]){
                //最低下标调整到分隔下标mid+1
                low = mid + 1;
                //斐波那契数列下标减两位
                k = k - 2;
            } else {
                //若mid <= high则说明mid即为查找到的位置，返回mid 若mid>high说明是补全数值，返回high
                return Math.min(mid,high);
            }
        }
        return -1;
    }
}
