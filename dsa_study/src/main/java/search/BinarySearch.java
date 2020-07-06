package search;

/**
 * @ClassName BinarySearch
 * @Description 二分查找--前提是有序，时间复杂度为O(logN)
 * @Author liangxp
 * @Date 2020/7/6 15:33
 **/
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 16, 24, 35, 47, 59, 62, 73, 88, 99};

        System.out.println(binsearch(arr,73));

        System.out.println(binsearch(arr,47));

        System.out.println(binsearch(arr,72));
        //求log
        System.out.println(Math.log((double) 10) / Math.log((double) 2));

    }
    /**
     * @description arr 要查找的数据 key 要查找的数据
     */
    public static int binsearch(int[] arr,int key){
        int low = 0;
        int high = arr.length - 1;
        int mid;
        //不在范围内，直接返回-1
        if (arr[low] > key || arr[high] < key) {
            return -1;
        }
        while(low <= high){
            //折半
            mid = (low + high) / 2;
            if (arr[mid] > key) {
                //最高下标调整到中位下标小一位
                high = mid - 1;
            } else if(arr[mid] < key){
                //最低下标调整到中位下标大一位
                low = mid + 1;
            } else {
                //说明找到
                return mid;
            }
        }
        return -1;
    }
}
