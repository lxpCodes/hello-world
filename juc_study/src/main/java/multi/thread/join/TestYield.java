package multi.thread.join;

/**
 * @descriptioon : 测试yield()方法
 * @auther : lxp
 * @date : 2018年4月20日
 * @time : 下午5:19:27
 */
public class TestYield extends Thread {
	
	public TestYield(String name){
		super(name);
	}
	
	public void run(){
		for (int i = 0; i < 50; i++) {
			System.out.println(this.getName() +"------" + i);
			try {
				Thread.sleep((int)Math.random() * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (i == 30) {
				System.out.println(this.getName() + "调用了yield()");
				this.yield();
			}
		}
	}
	
	public static void main(String[] args) {
		TestYield thread1 = new TestYield("张三");
		TestYield thread2 = new TestYield("李四");
		
		thread1.start();
		thread2.start();
	}
}
