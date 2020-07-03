package multi.thread.pool;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @descriptioon : 测试几种线程池
 * @auther : lxp
 * @date : 2018年4月20日
 * @time : 下午7:12:16
 */
public class Handler implements Runnable {
	
	private String  name;
	
	public Handler(String name){
		this.name = "thread "+name;
	}

	@Override
	public void run() {
		System.out.println(name + " Start Time:" + new Date());
		processCommand();
		System.out.println(name + " End Time:" + new Date());

	}
	
	public void processCommand(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	
	public static void main(String[] args) {
		System.out.println("Main Thread Start" + new Date());
		//1.带有缓存的线程池，线程执行顺序与放入顺序不一致
//		ExecutorService executorService = Executors.newCachedThreadPool();
		
		//2.创建固定数目的线程池，同一时刻最多只能有5个线程运行，执行完成后从线程池移除，不保证执行顺序
//		ExecutorService executorService = Executors.newFixedThreadPool(5);
		
		//3.创建一个只有一个线程的线程池,它能保证线程的先后顺序执行，并且能保证一条线程执行完成后才开启另一条新的线程
//		ExecutorService executorService = Executors.newSingleThreadExecutor();
		
//		for (int i = 0; i < 10; i++) {
//			executorService.execute(new Handler(String.valueOf(i)));
//		}
		
//		executorService.shutdown();
		//4.计划线程池类，它能设置线程执行的先后间隔及执行时间等，功能比上面的三个强大了一些。
		//创建一个大小10的线程池
		ScheduledThreadPoolExecutor poolExecutor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(10);
		
		for (int i = 0; i < 10; i++) {
			poolExecutor.schedule(new Handler(String.valueOf(i)), 10, TimeUnit.SECONDS);//延迟10秒执行
		}
		poolExecutor.shutdown();//不再接收添加新的线程，新加的会拒绝执行，线程执行完毕后会回收，但不会关闭线程池
		
		while (!poolExecutor.isTerminated()) {

		}
		System.out.println("Main Thread End" + new Date());
	}
}
