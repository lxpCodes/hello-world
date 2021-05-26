package multi.thread.contain;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @ClassName TicketSeller1
 * @Description QUeue实现卖票程序
 * @Author liangxp
 * @Date 2021/4/22 14:27
 **/
public class TicketSeller4 {
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while (true){
                    String str = tickets.poll();
                    if (str == null) {
                        break;
                    } else {
                        System.out.println("销售了--" + str);
                    }
                }
            }).start();

        }
    }
}
