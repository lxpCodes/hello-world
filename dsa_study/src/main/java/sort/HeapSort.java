package sort;

import java.util.Arrays;

/**
 * @ClassName HeapSort
 * @Description 堆排序-选择排序的一种改进
 * 堆是具有下列性质的二叉树：
 *      完全二叉树，一个重要的性质就是完全二叉树的节点能够完美的映射到数组中
 *      每个节点的值都大于或等于其左右孩子节点的值，称为大顶堆/最大堆
 *      每个节点的值都小于或等于其左右孩子节点的值，称为小顶堆/最小堆
 *      升序采用大顶堆，降序采用小顶堆
 * @Author liangxp
 * @Date 2020/6/27 17:00
 **/
public class HeapSort {
    public static void main(String[] args) {
        int arr [] = {32,43,23,13,5,26,78,49,100};
        /**
         * 堆排序
         * 	对简单选择排序的优化。
         将序列构建成大顶堆。
         将根节点与最后一个节点交换，然后断开最后一个节点。
         重复第一、二步，直到所有节点断开
         */

        System.out.println("开始排序");
        int arrayLength= arr.length;
        //循环建堆
        for(int i=0;i<arrayLength-1;i++){
            //建堆

            buildMaxHeap(arr,arrayLength-1-i);
            //交换堆顶和最后一个元素
            swap(arr,0,arrayLength-1-i);
            System.out.println(Arrays.toString(arr));
        }
        for (int i : arr) {
            System.out.print(i + "  ");
        }
    }
    public static void swap(int[] data, int i, int j) {
        int tmp=data[i];
        data[i]=data[j];
        data[j]=tmp;
    }
    //对data数组从0到lastIndex建大顶堆
    public static void buildMaxHeap(int[] data, int lastIndex) {
        //从lastIndex处节点（最后一个节点）的父节点开始
        for(int i=(lastIndex-1)/2;i >= 0;i--){
            //k保存正在判断的节点
            int k=i;
            //如果当前k节点的子节点存在
            while(k*2+1 <= lastIndex){
                //k节点的左子节点的索引
                int biggerIndex=2*k+1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if(biggerIndex < lastIndex){
                    //若果右子节点的值较大
                    if(data[biggerIndex] < data[biggerIndex+1]){
                        //biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }
                //如果k节点的值小于其较大的子节点的值
                if(data[k] < data[biggerIndex]){
                    //交换他们
                    swap(data,k,biggerIndex);
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k = biggerIndex;
                }else{
                    break;
                }
            }
        }
    }
}
