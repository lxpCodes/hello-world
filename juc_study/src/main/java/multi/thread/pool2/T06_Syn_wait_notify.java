package multi.thread.pool2;

/**
 * @ClassName T06_Syn_wait_notify
 * @Description 交替打印字符 使用wait notify
 * @Author liangxp
 * @Date 2021/4/23 11:23
 **/
public class T06_Syn_wait_notify {

    public static void main(String[] args) {
        final Object o = new Object();
        char[] c1 = "1234567".toCharArray();
        char[] c2 = "ABCDEFG".toCharArray();

        new Thread(() -> {
            synchronized (o){
                for (char c : c1) {
                    try {
                        o.notify();
                        o.wait();//让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print(c);
                }
                o.notify();//必须，否则无法停止程序
            }
        },"t1").start();

        new Thread(() -> {
            synchronized (o){
                for (char c : c2) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();//让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();//可以不写
            }
        },"t2").start();


    }
}
