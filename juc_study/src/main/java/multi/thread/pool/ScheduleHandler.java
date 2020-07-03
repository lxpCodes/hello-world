package multi.thread.pool;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @descriptioon : 测试可以定期执行任务或间隔执行的线程池
 * @auther : lxp
 * @date : 2018年4月20日
 * @time : 下午7:33:14
 */
public class ScheduleHandler implements Runnable{

	@Override
	public void run() {
		System.out.println("NowTime:" + new Date());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
//		scheduleAtFixedRate();
		
//		scheduleWithFixedDelay();
		scheduleTask(); 
	}

	/**
	 * 周期性执行一个任务
	 */
	public static void scheduleTask() {
		System.out.println("Start Time :" +new Date());
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);    
	    long oneDay = 24 * 60 * 60 * 1000;    
	    long initDelay  = getTimeMillis("19:50:00") - System.currentTimeMillis();    
	    initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;    
	    
	    executor.scheduleAtFixedRate(new ScheduleHandler(),initDelay,oneDay,TimeUnit.MILLISECONDS);
	}
	
	
	/**  
	 * 获取指定时间对应的毫秒数  
	 * @param time "HH:mm:ss"  
	 * @return  
	 */    
	private static long getTimeMillis(String time) {    
	    try {    
	        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");    
	        DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");    
	        Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);    
	        return curDate.getTime();    
	    } catch (ParseException e) {    
	        e.printStackTrace();    
	    }    
	    return 0;    
	}   
	
	/**
	 * 本次任务执行结束后，会延迟设定的时间才执行下一任务，两次任务间隔等于任务执行时间加间隔时间
	 */
	public static void scheduleWithFixedDelay() {
		System.out.println("Start Time :" +new Date());
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
		executorService.scheduleWithFixedDelay(new ScheduleHandler(), 0, 2000, TimeUnit.MILLISECONDS);//每隔2000毫秒执行一次
	}
	
	

	/**
	 * 每隔2000毫秒执行一次，当执行任务时间大于间隔，会等待该线程执行完毕
	 */
	public static void scheduleAtFixedRate() {
		System.out.println("Start Time :" +new Date());
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
		executorService.scheduleAtFixedRate(new ScheduleHandler(), 2000, 2000, TimeUnit.MILLISECONDS);//每隔2000毫秒执行一次
	}
}
