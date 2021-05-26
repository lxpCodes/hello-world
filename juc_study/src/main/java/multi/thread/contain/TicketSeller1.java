package multi.thread.contain;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TicketSeller1
 * @Description 卖票程序,超卖
 * @Author liangxp
 * @Date 2021/4/22 14:27
 **/
public class TicketSeller1 {
    static List<String> tickets = new ArrayList<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while (tickets.size() > 0){
                    System.out.println("销售了--" + tickets.remove(0));
                }
            }).start();

        }
    }
}
