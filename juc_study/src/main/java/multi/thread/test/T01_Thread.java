package multi.thread.test;

/**
 * @ClassName T01_Thread
 * @Description TODO
 * @Author liangxp
 * @Date 2020/6/25 13:02
 **/
public class T01_Thread {

    public static class T01_Thread_Sub extends Thread {
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("----T1----");
            }
        }
    }

    public static void main(String[] args) {
        new T01_Thread_Sub().start();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("----main----");
        }
    }

}
