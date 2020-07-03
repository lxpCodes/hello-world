package arith;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现获取最小值的栈
 * 思路：创建两个栈，一个主栈存数据，一个辅助栈存最小值所在索引
 * 	push时判断是否此时存入的值是否比原最小值小，如果是，则mins存入索引，如果不是，则mins不变
 *  pop时比较索引，索引不匹配，mins不出栈
 * 	        
 * @author xh_liangxp
 * 2018年10月31日
 */
public class MinStack {
	private List<Integer> data = new ArrayList<Integer>();
	private List<Integer> mins = new ArrayList<Integer>();
	
	/**
	 * 向栈内存元素
	 * @param num
	 * @throws Exception 
	 */
	public void push(int num) throws Exception{
		if (mins.size() == 0) {
			//初始化mins
			mins.add(0);
		} else {
			//辅助栈mins push最小值的索引
			int min = getMin();
			if (num < min) {
				mins.add(data.size()-1);
			}
			
		}
	}
	
	/**
	 * 从栈内取元素
	 * @throws Exception 
	 */
	public int pop() throws Exception{
		//栈为空，异常，返回-1
		if (data.size() == 0) {
			throw new Exception("栈为空");
		}
		//首先获取索引
		int popIndex = data.size() -1;
		//获取mins栈顶元素，它是最小值索引
		int minIndex = mins.get(mins.size()-1);
		//如果pop出去的索引就是最小值索引，mins才出栈
		if (popIndex == minIndex) {
			mins.remove(mins.size()-1);
		}
		return data.remove(data.size()-1);
	}
	
	/**
	 * 获取最小值
	 * @throws Exception 
	 */
	public int getMin() throws Exception{
		//栈为空，异常，返回-1
		if (mins.size() ==0) {
			throw new Exception("栈为空");
		}	
		//返回mins栈顶元素，是最小值索引
		int minIndex = mins.get(mins.size()-1);
		return data.get(minIndex);
	}
	
}
