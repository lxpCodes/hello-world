package sort;

/**
 * @ClassName QuickSort
 * @Description 快速排序-单轴实现
 * @Author liangxp
 * @Date 2020/6/27 17:04
 **/
public class QuickSort {
    public static void main(String[] args) {
        int arr [] = {32,43,23,13,5,26,78,49,100};

        quickSort(arr,0,arr.length -1);

        for (int i : arr) {
            System.out.print(i + "  ");
        }
    }

    /**
     * 快速排序
     * 	要求时间最快时。
     选择第一个数为p，小于p的数放在左边，大于p的数放在右边。
     递归的将p左边和右边的数都按照第一步进行，直到不能递归。
     */
    public static void quickSort(int[] numbers, int start, int end){
        if (start < end) {
            int base = numbers[start];
            //选定基准值，选第一个
            int temp;
            //临时中间值
            int i = start, j = end;
            do {
                while(numbers[i] < base && i < end)
                    i++;
                while(numbers[i] > base && j > start)
                    j--;
                if (i <= j) {
                    temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                    i++;
                    j--;
                }
            } while(i <= j);
            if (start < j) {
                quickSort(numbers, start, j);
            }
            if (end > i) {
                quickSort(numbers, i, end);
            }
        }
    }
}
