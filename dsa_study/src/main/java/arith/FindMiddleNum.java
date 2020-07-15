package arith;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @ClassName FindMiddleNum
 * @Description 找到数据流中的中位数
 * @Author liangxp
 * @Date 2020/7/14 11:43
 **/
public class FindMiddleNum {
    int count = 0;
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });

    public void insert(Integer num){
        if (count % 2 == 0){
            //偶数
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        } else {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }
        count ++;
        System.out.println(getMiddle());
    }

    private int getMiddle() {
        return maxHeap.peek();
    }


    public static void main(String[] args) {
        FindMiddleNum fm = new FindMiddleNum();
        fm.insert(1);
        fm.insert(2);
        fm.insert(0);
        fm.insert(20);
        fm.insert(10);
        fm.insert(22);


    }
}
