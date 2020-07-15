package search;

/**
 * @ClassName BinarySearch2
 * @Description 旋转后的有序数组，查找某个数
 * @Author liangxp
 * @Date 2020/7/9 9:16
 **/
public class BinarySearch2 {

    public static void main(String[] args) {
        int[] arr = {4,5,6,7,9,10,23,28,29,1,2,3};
        int target = 7;
        System.out.println(binarySearch(arr,target,0,arr.length-1));
    }

    private static int binarySearch(int[] arr, int target, int begin, int end){
        if (begin == end) {
            if (target == arr[begin]) {
                return begin;
            } else {
                return -1;
            }
        }

        int middle = (begin + end) / 2;
        if (target == arr[middle]) {
            return middle;
        }

        if (arr[begin] <= arr[middle-1]) {
            if (arr[begin] <= target && target <= arr[middle-1]) {
               return binarySearch(arr,target,begin,middle-1);
            } else {
               return binarySearch(arr,target,middle+1,end);
            }
        } else {
            if (arr[middle+1] <= target && target <= arr[end]) {
                return binarySearch(arr,target,middle+1,end);
            } else {
                return binarySearch(arr,target,begin,middle-1);
            }
        }
    }
}
