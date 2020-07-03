package multi.thread.pool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @descriptioon : 
 * @auther : lxp
 * @date : 2019年3月17日
 * @time : 下午10:22:29
 */
public class TestNewScheduledThreadPool {
	public static void main(String[] args) {
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
		for (int i = 0; i < 20; i++) {
			Runnable syncRunnable = new Runnable() {
				
				@Override
				public void run() {
					Date date = new Date();
					System.out.println(Thread.currentThread().getName()+"	time:" + date.getMinutes() + ":" + date.getSeconds());
					
				}
			};
//			executorService.schedule(syncRunnable, 3000, TimeUnit.MILLISECONDS);//延时一定时间之后执行
//			executorService.scheduleAtFixedRate(syncRunnable, 5000, 3000, TimeUnit.MILLISECONDS);//创建并执行一个在给定初始延迟后首次启用的定期操作，
			//后续操作具有给定的周期；也就是将在 initialDelay 后开始执行，然后在initialDelay+period 后执行，接着在 initialDelay + 2 * period 后执行，依此类推
			
			executorService.scheduleWithFixedDelay(syncRunnable, 5000, 3000, TimeUnit.MILLISECONDS);//创建并执行一个在给定初始延迟后首次启用的定期操作，
			//随后，在每一次执行终止和下一次执行开始之间都存在给定的延迟
		}
	}
}
