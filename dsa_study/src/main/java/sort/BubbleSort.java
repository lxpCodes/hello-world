package sort;

/**
 * @ClassName BubbleSort
 * @Description 冒泡排序-稳定排序
 * 将前一个数和后一个数进行比较,若前一个比后一个小则交换位置,一轮完成后将最大值排在最前方再开始第二轮,
 * 选出第二大的值,排在倒数第二的位置,直至排到顺数第二位置,完成排序
 * 外层循环控制循环次数，内层循环控制比较的两个数。
 * @Author liangxp
 * @Date 2020/6/27 17:02
 **/
public class BubbleSort {
    public static void main(String[] args) {
        int arr [] = {32,43,23,13,5,26,78,49,100};

        /**
         * 冒泡排序
         * 	一般不用。
         将序列中所有元素两两比较，将最大的放在最后面。
         将剩余序列中所有元素两两比较，将最大的放在最后面。
         重复第二步，直到只剩下一个数。
         *
         * 	设置循环次数。
         设置开始比较的位数，和结束的位数。
         两两比较，将最小的放到前面去。
         重复2、3步，直到循环次数完毕
         * @param arr
         */
        int length = arr.length;
        int temp;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - 1; j++) {
                if (arr[j] < arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
                System.out.print("第"+(i+1)+"次循环，第"+(j+1)+"次冒泡后数组为   ");
                for (int k: arr) {
                    System.out.print(k+"  ");
                }
                System.out.println();
            }
            System.out.print("第"+(i+1)+"次循环后数组为   ");
            for (int k: arr) {
                System.out.print(k+"  ");
            }
            System.out.println();
            System.out.println("==============================================");
        }

        for (int i : arr) {
            System.out.print(i + "  ");
        }
    }
}
