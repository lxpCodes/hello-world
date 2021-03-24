package sort;

import java.util.Arrays;

/**
 * @ClassName InsertSort
 * @Description 插入排序
 * 在排序过程中，左侧的数据默认是排好序的数据，而右侧的是还未被排序的数据。插入排序的思路就是从右侧的未排序区域内取出第一个数据，
 * 然后将它插入到左侧已排序区域内合适的位置上。当右侧区域数据取出排序只剩左侧区域，那么排序完毕
 * 外层循环控制右侧未被排序的数，内层循环控制左侧已被排序的数
 * 当表数据基本是有序时，那么插入排序速度将比冒泡排序、选择排序更快
 * 一般在输入规模大于1000的场合下不建议使用插入排序。
 * @Author liangxp
 * @Date 2020/6/27 16:53
 **/
public class InsertSort {
    public static void main(String[] args) {
        int arr [] = {32,43,23,13,5,26,78,49,100};
        insertSort1(arr);

//        insertSort2(arr);
    }

    public static void insertSort2(int[] arr){
        int j;
        //外层循环控制右侧未被排序的数 假设"第一个数"是"已经"排好序的,因此 未排序的数据从第二个数开始取
        for (int i = 1; i < arr.length; i++) {
            int norSort = arr[i];
            //内层循环控制左侧已被排序的数,从最大的已排序的数开始比较
            //如果未排序的数小于已排序的数arr[j],则将arr[j]像右移动一位
            for (j = i - 1; j >= 0 && norSort < arr[j]; j--) {
                arr[j + 1] = arr[j];
            }
            //如果未排序的数大于已排序的数arr[j],则将arr[j+1]赋值给norSort,这就是为排序的数需要插入的已排序数据中的位置
            arr[j + 1] = norSort;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort1(int[] arr){
        /**
         * 直接插入排序
         * 首先设定插入次数，即循环次数，for(int i=1;i<length;i++)，1个数的那次不用插入。
         * 设定插入数和得到已经排好序列的最后一个数的位数。insertNum和j=i-1。
         * 从最后一个数开始向前循环，如果插入数小于当前数，就将当前数向后移动一位。
         * 将当前数放置到空着的位置，即j+1。
         * @param arr
         */
        int length = arr.length;
        int insertNum;//要插入的数
        for (int i = 1; i < length; i++) {
            //插入的次数
            insertNum = arr[i];
            //要插入的数
            System.out.println("第" + i +"次循环插入的数为  " +insertNum);
            int j = i - 1;
            //已经排好序的序列元素个数
            System.out.println("已经排好序的元素个数 " + j);
            while(j >= 0 && arr[j] > insertNum){
                System.out.println("序列从后向前，将"+arr[j] +"移动到原"+arr[j+1]+"位置上");
                arr[j+1] = arr[j];
                //序列从后到前循环，将大于insertNum的数向后移动一格
                j --;
                //完整移动一格
                System.out.println("j减1后为  " + j);
            }
            arr[j+1] = insertNum;
            //将需要插入的数放在要插入的位置
            System.out.println("将"+insertNum +"插入到数组"+(j+1)+"位置上");
            System.out.print("此时数组为   ");
            for (int k : arr) {
                System.out.print(k + "  ");
            }
            System.out.println();
        }

        for (int i : arr) {
            System.out.print(i + "  ");
        }
    }
}
