package multi.thread.join;

/**
 * @descriptioon : 测试join()方法启动线程后直接调用，即join()的作用是：“等待该线程终止”，
 * 这里需要理解的就是该线程是指的主线程等待子线程的终止。也就是在子线程调用了join()方法后面的代码，
 * 只有等到子线程结束了才能执行。
 * @auther : lxp
 * @date : 2018年4月20日
 * @time : 下午4:22:35
 */
public class TestJoin {
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + "线程开始运行");
		Thread1 thread1 = new Thread1("A");
		Thread1 thread2 = new Thread1("B");
		thread1.setPriority(Thread.MIN_PRIORITY);
		thread2.setPriority(Thread.MAX_PRIORITY);
		thread1.start();
		thread2.start();
		
//		try {
//			thread1.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//  		try {
//			thread2.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		System.out.println(Thread.currentThread().getName() + "线程结束运行");
	}
}


class Thread1 extends Thread{
	private String threadName;
	
	public Thread1(String name){
		this.threadName = name;
	}
	public void run(){
		System.out.println("子线程 "+Thread.currentThread().getName() + " 开始运行");
		for (int i = 0; i < 5; i++) {
			
			System.out.println("子线程 "+threadName + " 运行ing");
			
			try {
				Thread.sleep((int)Math.random() * 100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("子线程 "+Thread.currentThread().getName() + " 结束运行");
	}
}
