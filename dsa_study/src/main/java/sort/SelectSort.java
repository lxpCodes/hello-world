package sort;

/**
 * @ClassName SelectSort
 * @Description 简单选择排序--不稳定排序
 * 第一轮，使用第一个值，索引为i，依次与后面的值做比较,并使用临时变量min=i，
 * 记录比较后的相对较小的值的索引，内层循环完毕之后，判断如果min不等于第一个元素的下标i，
 * 就让第一个元素跟他交换一下值，这样就找到整个数组中最小的数了，
 * 一轮结束后，此时将最小的值排在了最前方；再循环拿第二个值与后面的值依次作比较,直至倒数第二个值完成比较,即完成排序
 * @Author liangxp
 * @Date 2020/6/27 16:59
 **/
public class SelectSort {
    public static void main(String[] args) {
        int arr [] = {32,43,23,13,5,26,78,49,100};
        /**
         * 简单选择排序
         *  常用于取序列中最大最小的几个数时。
         (如果每次比较都交换，那么就是交换排序；如果每次比较完一个循环再交换，就是简单选择排序。)
         遍历整个序列，将最小的数放在最前面。
         遍历剩下的序列，将最小的数放在最前面。
         重复第二步，直到只剩下一个数。
         *
         *  首先确定循环次数，并且记住当前数字和当前位置。
         将当前位置后面所有的数与当前数字进行对比，小数赋值给key，并记住小数的位置。
         比对完成后，将最小的值与第一个数的值交换。
         * @param arr
         */
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            //循环次数
            int min = arr[i];
            int position = i;
            for (int j = i+1; j < length; j++) {
                //选出最小值和位置
                if (arr[j] < min) {
                    min = arr[j];
                    position = j;
                }
            }
            arr[position] = arr[i];
            //交换位置
            arr[i] = min;
        }

        for (int i : arr) {
            System.out.print(i + "  ");
        }
    }
}
