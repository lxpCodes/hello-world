package multi.thread.volatil;

import java.util.concurrent.TimeUnit;

/**
 * @descriptioon : 
 * @auther : lxp
 * @date : 2019年3月18日
 * @time : 下午8:12:47
 */
public class VisibilityDemo {
	private static volatile boolean flag = true;
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				int i = 0;
				while (VisibilityDemo.flag) {
//					synchronized (this) {
//						i ++;
//					}
					i ++;
				}
				System.out.println(i);
				
			}
		}).start();
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		VisibilityDemo.flag = false;
		System.out.println(flag);
		
	}

}
