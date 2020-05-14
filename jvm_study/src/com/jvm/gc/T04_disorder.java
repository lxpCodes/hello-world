package com.jvm.gc;

/**
 * @ClassName T04_disorder
 * @Description 证明指令重排序
 * @Author liangxp
 * @Date 2020/5/13 17:38
 **/
public class T04_disorder {

    private static int x = 0, y= 0;
    private static int a = 0, b = 0;


    public static void main(String[] args) throws InterruptedException {
        int j = 0;
        for (;;) {
            /**
             * @description 假定不存在指令重排序，可能的执行顺序，不会出现x y 同时为0的情况
             * 顺序一：  线程1        线程2
             *          a = 1;
             *          x = b;
             *                       b = 1;
             *                       y = a;
             * 结果:    x = 0 y = 1
             *
             * 顺序二：  线程1        线程2
             *           a = 1;
             *                       b = 1;
             *                       y = a;
             *           x = b;
             * 结果：    x = 1 y = 1
             *
             * 顺序三：  线程1        线程2
             *           a = 1;
             *                      b = 1;
             *           x = b;
             *                      y = a;
             * 结果：    x = 1 y = 1
             *
             * @author liangxp
             * @date 2020/5/14 9:55
             */
            x = 0; y = 0;
            a = 0 ;b = 0;
            Thread one = new Thread(() -> {
               a = 1;
               x = b;
            });

            Thread other = new Thread(new Runnable() {
                public void run() {
                    b = 1;
                    y = a;
                }
            });
            j++;

            one.start();
            other.start();
            one.join();
            other.join();

            String result = "第" + j + "次 （" + x + "," + y + "）";
            if (x  == 0 && y == 0){
                System.out.println(result);
                break;
            } else {

            }
        }
    }
}
