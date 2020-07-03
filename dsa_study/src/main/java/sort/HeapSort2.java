package sort;

import java.util.Arrays;

/**
 * @ClassName HeapSort2
 * @Description 堆排序1
 * @Author liangxp
 * @Date 2020/6/28 15:24
 **/
public class HeapSort2 {
    public static void main(String[] args) {
        int[] arr = new int[]{49, 38, 65, 97, 76, 13, 27, 49, 78};
        //大顶堆排序 升序
        bigHeapSort(arr);
        System.out.println(Arrays.toString(arr));
        //小顶堆排序 降序
        smallHeapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void smallHeapSort(int[] arr) {
        /*1、构建小顶堆*/
        /*i从最后一个非叶子节点的索引开始，递减构建，直到i=-1结束循环
        这里元素的索引是从0开始的，所以最后一个非叶子节点array.length/2 - 1，这是利用了完全二叉树的性质*/
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            buildSmallHeap(arr, i, arr.length);
        }
        /*2、开始堆排序，i = arr.length - 1，即从小顶堆尾部的数开始，直到i=0结束循环*/
        for (int i = arr.length - 1; i > 0; i--) {
            //交换堆顶与堆尾元素顺序
            swap(arr, 0, i);
            //重新构建小顶堆，此时堆的大小为交换前堆大小-1
            buildSmallHeap(arr, 0, i);
        }
    }

    private static void buildSmallHeap(int[] arr, int i, int length) {
        //先把当前非叶子节点元素取出来，因为当前元素可能要一直移动
        int temp;
        //节点的子节点的索引
        int childIndex;
        /*循环判断父节点是否大于两个子节点,如果左子节点索引大于等于堆长度 或者父节点大于两个子节点 则结束循环*/
        for (temp = arr[i]; (childIndex = 2 * i + 1) < length; i = childIndex) {
            //childIndex + 1 < length 说明该节点具有右子节点,并且如果如果右子节点的值小于左子节点，那么childIndex自增1，即childIndex指向右子节点索引
            if (childIndex + 1 < length && arr[childIndex] > arr[childIndex + 1]) {
                childIndex++;
            }
            //如果发现最小子节点(左、右子节点)小于根节点，为了满足小顶堆根节点的值小于子节点，需要进行值的交换
            //如果子节点更换了，那么，以子节点为根的子树会受到影响,所以，交换之后继续循环对子节点所在的树进行判断
            if (arr[childIndex] < temp) {
                swap(arr, i, childIndex);
            } else {
                //走到这里,说明父节点小于最小的子节点，满足最小堆的条件，直接终止循环
                break;
            }
        }
    }

    private static void bigHeapSort(int[] arr) {
        //1、从最后一个非叶子节点的索引构建，递减构建，直到i=-1结束循环
        //这里元素的索引是从0开始的，所以最后一个非叶子节点array.length/2 - 1，这是利用了完全二叉树的性质
        for (int i = arr.length - 1; i >= 0; i--) {
            buildBigHeap(arr, i, arr.length);
        }
        //2、开始堆排序，i=arr.length - 1 从大顶堆尾部的数开始，直到i=0结束循环
        for (int i = arr.length - 1; i > 0; i--) {
            //交换堆顶与堆尾元素顺序
            swap(arr, 0, i);
            //重新构建大顶堆
            buildBigHeap(arr, 0, i);
        }
    }

    private static void buildBigHeap(int[] arr, int i, int length) {
        //先取当前非叶子节点元素，因为可能要一直移动
        int temp;
        //节点的子节点的索引
        int childIndex;
        //循环判断父节点是否大于两个子节点，如果左子节点索引大于等于堆长度，或者父节点大于两个子节点，结束循环
        for (temp = arr[i]; (childIndex = 2 * i + 1) < length; i = childIndex){
            //childIndex + 1 < length 说明该节点具有右子节点,并且如果右子节点的值大于左子节点，
            // 那么childIndex自增1，即childIndex指向右子节点索引
            if (childIndex + 1 < length && arr[childIndex] < arr[childIndex + 1]) {
                childIndex++;
            }
            //如果发现最大子节点(左、右子节点)大于根节点，为了满足大顶堆根节点的值大于子节点，需要进行值的交换
            //如果子节点更换了，那么，以子节点为根的子树会受到影响,所以，交换之后继续循环对子节点所在的树进行判断
            if (arr[childIndex] > temp) {
                swap(arr, i, childIndex);
            } else {
                //走到这里,说明父节点大于最大的子节点，满足最大堆的条件，直接终止循环
                break;
            }
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }
}
