package multi.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @descriptioon : 适用执行一些生存期很短的异步型任务
 * @auther : lxp
 * @date : 2019年3月17日
 * @time : 下午10:18:51
 */
public class TestNewCachedThreadPool {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+"线程调用了");
					
				}
			};
			System.out.println("======"+i+"======");
			executorService.execute(runnable);
			
		}
        System.out.println(1 << 16);
	}
}
