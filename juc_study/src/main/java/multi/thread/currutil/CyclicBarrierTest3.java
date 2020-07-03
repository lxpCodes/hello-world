package multi.thread.currutil;

import java.util.concurrent.*;
import java.util.Map.Entry;

/**
 * @ClassName CyclicBarrierTest3
 * @Description 测试CyclicBarrier使用
 * @Author liangxp
 * @Date 2020/6/17 15:50
 **/
public class CyclicBarrierTest3 implements Runnable{

    private CyclicBarrier barrier = new CyclicBarrier(4,this);

    private Executor executor = Executors.newFixedThreadPool(4);

    private ConcurrentHashMap<String,Integer> sheetBankWater = new ConcurrentHashMap<>();

    private void count(){
        for (int i = 0; i < 4; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    sheetBankWater.put(Thread.currentThread().getName(),1);
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void run() {
        int result = 0;
        //汇总
        for (Entry<String,Integer> sheet : sheetBankWater.entrySet()) {
            result += sheet.getValue();
        }

        sheetBankWater.put("result",result);
        System.out.println("result: "+result);
    }

    public static void main(String[] args) {
        CyclicBarrierTest3 cyclic = new CyclicBarrierTest3();
        cyclic.count();
        System.out.println("number waiting :" +cyclic.barrier.getNumberWaiting());
    }
}
