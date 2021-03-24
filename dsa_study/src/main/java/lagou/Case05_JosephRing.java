package lagou;

import java.util.LinkedList;

/**
 * @ClassName Case05_JosephRing
 * @Description 循环队列处理约瑟夫环问题
 * @Author liangxp
 * @Date 2020/9/29 18:10
 **/
public class Case05_JosephRing {
    public static void main(String[] args) {

        ring(10, 5);
    }


    public static void ring(int n, int m) {
        LinkedList<Integer> q = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) {
            q.add(i);
        }
        int k = 2;
        int element = 0;
        int i = 0;
        for (; i<k; i++) {
            element = q.poll();
            q.add(element);
        }
        i = 1;
        while (q.size() > 0) {
            element = q.poll();
            if (i < m) {
                q.add(element);
                i++;
            } else {
                i = 1;
                System.out.println(element);
            }
        }
    }
}
