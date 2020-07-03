package multi.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @descriptioon : 
 * @auther : lxp
 * @date : 2019年3月17日
 * @time : 下午10:15:49
 */
public class TestNewFixedThreadPool {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 20; i++) {
			Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
					
				}
			};
			executorService.execute(runnable);
		}
	}
}
