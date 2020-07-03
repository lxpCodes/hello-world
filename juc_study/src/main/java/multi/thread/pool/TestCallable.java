package multi.thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @descriptioon : 测试有返回值的多线程
 * @auther : lxp
 * @date : 2018年4月20日
 * @time : 下午7:57:11
 */
public class TestCallable implements Callable<String> {
	
	private int id;
	public TestCallable(int id) {
		this.id = id;
	}
	/**
	 * 一旦任务传给ExecutorService的submit方法，则该方法自动在一个线程上执行。
	 */
	@Override
	public String call() throws Exception {
		System.out.println("call()方法被自动调用,干活！！！             " + Thread.currentThread().getName());   
        //一个模拟耗时的操作  
        for (int i = 999999; i > 0; i--){
        	
        }   
        return"call()方法被自动调用，任务的结果是：" + id + "    " + Thread.currentThread().getName();   
	}   
	
	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newCachedThreadPool();   
        List<Future<String>> resultList = new ArrayList<Future<String>>();   

        //创建10个任务并执行  
        for (int i = 0; i < 10; i++) {   
                //使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中  
                Future<String> future = executorService.submit(new TestCallable(i));   
                //将任务执行结果存储到List中  
                resultList.add(future);   
        }   
      //启动一次顺序关闭，执行以前提交的任务，但不接受新任务。如果已经关闭，则调用没有其他作用。  
        executorService.shutdown();   
          
        //遍历任务的结果  
        for (Future<String> fs : resultList) {   
            try {   
                  System.out.println(fs.get());     //打印各个线程（任务）执行的结果  
            } catch (InterruptedException e) {   
                    e.printStackTrace();   
            } catch (ExecutionException e) {   
                    e.printStackTrace();   
            }
        }   
	}
}
