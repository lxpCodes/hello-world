package multi.thread.cow;

import java.util.PriorityQueue;

/**
 * @ClassName T07_01_PriorityQueue
 * @Description 优先级队列测试
 * @Author liangxp
 * @Date 2021/4/23 10:59
 **/
public class T07_01_PriorityQueue {

    public static void main(String[] args) {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();

        priorityQueue.add("c");
        priorityQueue.add("b");
        priorityQueue.add("a");
        priorityQueue.add("e");
        priorityQueue.add("d");
        priorityQueue.add("f");

        for (int i = 0; i < 6; i++) {
            System.out.println(priorityQueue.poll());
        }
    }
}
