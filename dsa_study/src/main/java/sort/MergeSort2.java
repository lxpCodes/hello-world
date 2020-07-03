package sort;

import java.util.Arrays;

/**
 * @ClassName MergeSort2
 * @Description 归并排序
 *
 * @Author liangxp
 * @Date 2020/7/2 14:27
 **/
public class MergeSort2 {

    public static void main(String[] args) {
        int[] arr = new int[]{49, 38, 65, 97, 76, 13, 27, 49, 78};
        mergeSort(arr, new int[arr.length], 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }


    /**
     * @description
     * @author liangxp
     * @date 2020/7/2 14:28
     * @params [arr, newArr, left, right]
     * @return void
     */
    private static void mergeSort(int[] arr, int[] newArr, int left, int right) {
        if (left < right) {
            //1.算出拆分种植，作为左数组的结束索引
            int mid = (left + right) / 2;
            //拆分左子数组，然后将左子数组作为父数组继续递归拆分，直到拆分为只有一个元素
            mergeSort(arr,newArr,left, mid);
            //拆分右子数组，然后新旧递归拆分，直到拆分为只有一个元素
            mergeSort(arr,newArr, mid + 1,right);
            //2.拆到不能再拆，合并数组
            merge(arr,newArr, left, mid,mid + 1, right);
        }
    }


    private static void merge(int[] arr, int[] newArr, int leftStart, int leftEnd, int rightStart, int rightEnd) {
        //备份获取起始索引 两个相邻子数组的元素个数
        int m = leftStart;
        int numElements = rightEnd - leftStart + 1;
        //如果左数组起始位置小于等于左结束位置，并且右数组起始位置小于等于右结束位置，
        //那么比较它们相同的相对位置的元素大小，并且将较小的元素加入到新的数组对应的索引位置（从左起始索引开始）中
        //然后被添加的元素位置相应的自增1,继续循环比较,直到其中一个条件不满足,结束循环
        while (leftStart <= leftEnd && rightStart <= rightEnd){
            newArr[m++] = arr[leftStart] <= arr[rightStart] ?arr[leftStart++] : arr[rightStart++];
        }
        //如果左数组起始位置小于等于左结束位置，说明上面循环并没有将左数组的元素添加完毕，继续添加
        while (leftStart <= leftEnd){
            newArr [m++] = arr [leftStart++];
        }
        //如果右数组起始位置小于等于右结束位置，说明上面循环并没有将右数组的元素添加完毕，继续添加
        while(rightStart <= rightEnd){
            newArr [m++] = arr [rightStart++];
        }
        //将新数组的元素拷贝到原数组对应索引处，保证后续排序合并元素的有序性
        for (int i = 0; i < numElements; i++,rightEnd--) {
            arr[rightEnd] = newArr [rightEnd];
        }

    }
}
