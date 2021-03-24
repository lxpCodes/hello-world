package multi.thread.test;

/**
 * @ClassName T02_ThreadState
 * @Description 线程状态转换
 * @Author liangxp
 * @Date 2020/6/25 18:28
 **/
public class T02_ThreadState {

    static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println(this.getState());

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mm();
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new MyThread();
        System.out.println("first: "+t.getState());
        t.start();

        t.join();

        System.out.println("second:"+t.getState());

    }

    public static void mm(){
        synchronized (T02_ThreadState.class){

        }
    }
}
