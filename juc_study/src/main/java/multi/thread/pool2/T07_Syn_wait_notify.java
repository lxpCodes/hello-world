package multi.thread.pool2;

/**
 * @ClassName T07_Syn_wait_notify
 * @Description 实现交替打印
 * @Author liangxp
 * @Date 2021/4/23 11:30
 **/
public class T07_Syn_wait_notify {
    private static volatile boolean t2started = false;

    public static void main(String[] args) {
        final Object o = new Object();
        char[] c1 = "1234567".toCharArray();
        char[] c2 = "ABCDEFG".toCharArray();

        new Thread(() -> {
            synchronized (o){
                while (!t2started){
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (char c : c1) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();//让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();//必须，否则无法停止程序
            }
        },"t1").start();

        new Thread(() -> {
            synchronized (o){
                for (char c : c2) {
                    System.out.print(c);
                    t2started = true;
                    try {
                        o.notify();
                        o.wait();//让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();//必须，否则无法停止程序
            }
        },"t2").start();

    }

}
