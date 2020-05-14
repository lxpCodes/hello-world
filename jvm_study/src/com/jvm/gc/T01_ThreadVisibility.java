package com.jvm.gc;

/**
 * @ClassName T01_ThreadVisibility
 * @Description volatile 可见性测试
 * @Author liangxp
 * @Date 2020/5/13 17:27
 **/
public class T01_ThreadVisibility {

    private static /*volatile */boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() ->{
            while (flag){

            }
            System.out.println("end");

        },"serverStart").start();

        Thread.sleep(1000L);

        flag = false;


    }
}
