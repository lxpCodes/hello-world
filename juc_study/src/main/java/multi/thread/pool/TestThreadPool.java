package multi.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @descriptioon : 
 * @auther : lxp
 * @date : 2019年3月17日
 * @time : 下午10:50:34
 */
public class TestThreadPool {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
//		ExecutorService executorService = Executors.newFixedThreadPool(5);
//		ExecutorService executorService = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			executorService.execute(new TestRunnable());
			System.out.println("=======a"+ i +"======");
		}
		executorService.shutdown();
		
	}
}


class TestRunnable implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() +"线程调用了");
		
	}
	
}
