package multi.thread.cow;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @ClassName T09_TransferQueue
 * @Description TransferQueue测试
 * @Author liangxp
 * @Date 2021/4/23 11:07
 **/
public class T09_TransferQueue {

    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread(() ->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.transfer("aaa");

        strs.put("aaa");

        new Thread(() ->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }
}
