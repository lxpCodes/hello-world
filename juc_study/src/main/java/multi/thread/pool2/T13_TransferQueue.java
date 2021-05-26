package multi.thread.pool2;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * @ClassName T13_TransferQueue
 * @Description TransQueue实现交替打印
 * @Author liangxp
 * @Date 2021/4/23 17:31
 **/
public class T13_TransferQueue {
    public static void main(String[] args) {
        char[] c1 = "1234567".toCharArray();
        char[] c2 = "ABCDEFG".toCharArray();

        TransferQueue<Character> queue = new LinkedTransferQueue<>();
        new Thread(() ->{
            try {
                for (char c : c1) {
                    System.out.println(queue.take());
                    queue.transfer(c);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();


        new Thread(() ->{
            try {
                for (char c : c2) {
                    queue.transfer(c);
                    System.out.println(queue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();

    }
}
