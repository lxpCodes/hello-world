package sort;

import java.util.Arrays;

/**
 * @ClassName QuickSort2
 * @Description 快速排序-双轴排序,数组元素少于某个值时采用插入排序
 * @Author liangxp
 * @Date 2020/7/2 15:41
 **/
public class QuickSort2 {

    /*
     * 当数组长度小于等于4时,采用直接插入排序(这里为了演示,值取得比较小,实际值可以更大一些)
     */
    private static final int INSERTION_SORT_THRESHOLD = 4;

    public static void main(String[] args) {
        int[] arr = new int[]{38, 49, 65, 97, 49, 64, 27, 49, 78};

        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSort(arr,0,arr.length - 1);
    }

    private static void quickSort(int[] arr,int left, int right){
        if (right - left + 1 > INSERTION_SORT_THRESHOLD) {
            //分割
            int baseIndex = partition(arr,left,right);
            quickSort(arr,left, baseIndex - 1);
            quickSort(arr, baseIndex + 1, right);
        } else {
            insertSort(arr,left,right);
        }
    }

    private static void insertSort(int[] arr, int left, int right) {
        int j;
        for (int p = left + 1; p <= right; p++) {
            int noSort = arr[p];
            for (j = p - 1; j >= left && noSort < arr[j]; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = noSort;
        }
    }

    /**
     * @description 分割数组  找一个基准值,将数组分成两部分,一部分比基准值小,另一部分比基准值大
     * @author liangxp
     * @date 2020/7/2 15:47
     * @params [arr, left, right]
     * @return int
     */
    private static int partition(int[] arr, int left, int right) {
        //三数取中法获取基准值
        int base = median3(arr,left,right);
        //开始分割
        int i = left, j = right;
        while(true){
            //先从左向右找,然后从右向左找,顺序不能乱,如果乱了那么需要改变代码
            //从左往右边找,直到找到大于等于base值的数
            //i一定小于等于j,等于j时说明left所在的数base就是最大数
            while (arr[i] < base && i < j){
                i++;
            }
            while (arr[j] > base) {
                --j;
            }
            if (i < j){
                swap(arr,i,j);
            } else {
                break;
            }
            //如果还要继续分割,那么j--,该操作可以让与base相同的j继续向中间靠拢而不是停在原地
            --j;
        }
        /*3、寻找基准值的在数组的正确位置*/
        arr[left] = arr[j];
        arr[j] = base;
        /*4、返回基准值的正确索引*/
        return j;
    }

    private static void swap(int[] arr,int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //三数取中法
    private static int median3(int[] arr, int left, int right) {
        //计算数组中间元素下标
        int center = (left + right) / 2;
        //交换左端与右端数据,保证left小于等于right
        if (arr[right] < arr[left]) {
            swap(arr,left,right);
        }
        //交换中间与右端数据,保证中间小于等于right
        if (arr[right] < arr[center]){
            swap(arr,center, right);
        }
        //交换中间与左端数据,保证中间小于等于left
        if (arr[left] < arr[center]) {
            swap(arr,left, center);
        }
        //经过交换,此时left为返回的最终的基准值,即三数的中间值,并且一定能有如下规则center<=left<=right
        // 为此我们可以进一步交换,让数组尽量变得"更加有序",注意该步骤是可以省略的
        swap(arr, center, left + 1);
        return arr[left];
    }
}
