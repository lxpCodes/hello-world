package multi.thread.join;

/**
 * @descriptioon : 建立三个线程，A线程打印10次A，B线程打印10次B,C线程打印10次C，要求线程同时运行，交替打印10次ABC。
 * @auther : lxp
 * @date : 2018年4月20日
 * @time : 下午5:37:44
 */
public class TestPrintByOrder extends Thread {
	private String name;
	private Object prev;
	private Object self;
	
	public TestPrintByOrder(String name,Object prev,Object self){
		this.name = name;
		this.prev = prev;
		this.self = self;
	}
	
	public void run(){
		int count = 10;
		while (count > 0) {
			synchronized (prev) {
				System.out.println(prev);
				synchronized (self) {
					System.out.println(self);
					System.out.println(name);
					count --;
					
					self.notify();
				}
				try {
					prev.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Object a = new Object();
		Object b = new Object();
		Object c = new Object();
		Object d = new Object();
		
		TestPrintByOrder thread1 = new TestPrintByOrder("A",d,a);
		TestPrintByOrder thread2 = new TestPrintByOrder("B",a,b);
		TestPrintByOrder thread3 = new TestPrintByOrder("C",b,c);
		TestPrintByOrder thread4 = new TestPrintByOrder("D",c,d);
		
		thread1.start();
		thread1.sleep(100);
		thread2.start();
		thread2.sleep(100);
		thread3.start();
		thread3.sleep(100);
		thread4.start();
		thread4.sleep(100);
	}
}
