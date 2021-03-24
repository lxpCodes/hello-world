package lagou;

/**
 * @ClassName Case13_BinarySearchNum
 * @Description 二分查找数组指定数字
 * @Author liangxp
 * @Date 2020/9/30 15:43
 **/
public class Case13_BinarySearchNum {
    public static void main(String[] args) {
        // 需要查找的数字
        int targetNumb = 16;
        // 目标有序数组
        int[] arr = { 1, 2, 3, 4, 5, 16, 27, 28, 34, 50 };
        int middle = 0;
        int low = 0;
        int high = arr.length -1;
        int isfind = 0;
        while (low <= high){
            middle = (high + low) / 2;
            // 元素过多时，high + low容易溢出，使用high + (low - high)/2 避免
            if (arr[middle] == targetNumb){
                System.out.println(targetNumb + " 在数组中，下标为:" + middle);
                isfind = 1;
                break;
            } else if (arr[middle] > targetNumb){
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        if(isfind == 0){
            System.out.println("数组不含" + targetNumb);
        }
    }
}
