package multi.thread.cow;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @ClassName T04_ConcurrentQueue
 * @Description 测试阻塞队列
 * @Author liangxp
 * @Date 2021/4/22 17:56
 **/
public class T04_ConcurrentQueue {
    public static void main(String[] args) {
        Queue<String> queue = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 10; i++) {
            queue.offer("a" + i);
        }

        System.out.println(queue);

        System.out.println(queue.size());

        System.out.println(queue.poll());
        System.out.println(queue.size());

        System.out.println(queue.peek());
        System.out.println(queue.size());

        int num = queue.size();
        for (;;) {
            num ++;
            queue.offer("a" + num);
            System.out.println(queue.size());
        }
    }
}
