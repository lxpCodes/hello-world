package search;

/**
 * @ClassName InterpolationSearch
 * @Description 插值查找--插值查找是根据要查找的关键字key与查找表中最大最小记录的关键字比较后的查找方法
 *              mid = low + (high - low)/2
 *              优化为mid = low + ((key - a[low])/(a[high] - a[low])) * (high - low)
 *              即动态获取mid,适合分布比较均匀的查找表
 * @Author liangxp
 * @Date 2020/7/6 15:50
 **/
public class InterpolationSearch {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 16, 24, 35, 47, 59, 62, 73, 88, 99};

        System.out.println(interSearch(arr,59));
        System.out.println(interSearch(arr,99));
        System.out.println(interSearch(arr,134));


    }


    public static int interSearch(int[] arr,int key){
        int low = 0;
        int high = arr.length - 1;
        int mid;
        if (arr[low] > key || arr[high] < key) {
            return -1;
        }
        while (low <= high){
            //插值计算mid
            mid = low + (high - low ) * (key - arr[low]) / (arr[high] - arr[low]);
            if (arr[mid] > key) {
                //最高下标调整到中位下标小一位
                high = mid -1;
            } else if(arr[mid] < key){
                //最低下标调整到中位下标大一位
                low = mid + 1;
            } else {
                return mid;
            }
        }
        //查不到返回-1
        return -1;
    }
}
