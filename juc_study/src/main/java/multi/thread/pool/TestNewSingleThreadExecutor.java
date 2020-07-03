package multi.thread.pool;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @descriptioon : 
 * @auther : lxp
 * @date : 2019年3月17日
 * @time : 下午10:40:56
 */
public class TestNewSingleThreadExecutor {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 20; i++) {
			Runnable syncRunnable = new Runnable() {

				@Override
				public void run() {
					Date date = new Date();
					System.out.println(Thread.currentThread().getName()+"	time:" + date.getMinutes() + ":" + date.getSeconds());

				}
			};
			executorService.execute(syncRunnable);
		}
	}
}
