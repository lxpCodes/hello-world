package arith;

import java.util.Random;

/**
 * 从1000个数中找到前50
 * @author xh_liangxp
 * 2018年10月30日
 */
public class TopN {
	//父节点
	private static int parent(int n){
		return (n-1) / 2;
	}
	
	//左孩子
	private static int left(int n){
		return 2 * n + 1;
	}
	
	//右孩子
	private static int right(int n){
		return 2 * n + 2;
	}
	//构建堆,n代表所构建小顶堆的数量 data代表待选择的数组
	private static void buildHeap(int n,int[] data){
		for (int i = 1; i < n; i++) {
			int t = i;
			//调整堆
			while(t != 0 && data[parent(t)] > data[t]) {
				int temp = data[t];
				data[t] = data[parent(t)];
				data[parent(t)] = temp;
				t = parent(t);
			}
		}
	}
	
	//调整data[i] i为待比较的数据所在位置下标，n为构建的小顶堆的数量  data为待筛选数组
	private static void adjust(int i, int n ,int[] data){
		if (data[i] <= data[0]) {
			return;
		}
		//置换堆顶
		int temp = data[i];
		data[i] = data[0];
		data[0] = temp;
		//调整堆顶
		int t = 0;
		//如果左右子节点都在选取点左侧，且
		while( (left(t) < n && data[t] > data[left(t)]) 
				|| (right(t) < n && data[t] > data[right(t)]) ){
			if (right(t) < n && data[right(t)] < data[left(t)]) {
				//右孩子更小，置换右孩子
				temp = data[t];
				data[t] = data[right(t)];
				data[right(t)] = temp;
				t = right(t);
			} else {
				//否则置换左孩子
				temp = data[t];
				data[t] = data[left(t)];
				data[left(t)] = temp;
				t = left(t);
			}
		}
	} 
	
	//寻找topN，该方法改变data，将topN排到最前面
	public static void findTopN(int n, int[] data){
		//先构建n个数的小顶堆
		buildHeap(n, data);
		//n往后的数进行调整
		for (int i = 0; i < data.length; i++) {
			adjust(i, n, data);
		}
	}

	//打印数组
	public static void print(int[] data){
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		System.out.println("===============第一组测试================");
		int[] arr1 = new int[]{56,30,71,18,29,93,44,75,20,65,68,34};
		System.out.println("原数组 ：");
		print(arr1);
		findTopN(5, arr1);
		System.out.println("调整后数组：");
		print(arr1);
		
		System.out.println("===============第二组测试================");
		int[] arr2 = new int[1000];
		Random random2 = new Random();
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = random2.nextInt() + i;
		}
		System.out.println("原数组 ：");
		print(arr2);
		findTopN(50, arr2);
		System.out.println("调整后数组：");
		print(arr2);
		
		System.out.println("===============第三组测试================");
		int[] arr3 = new int[1000];
		Random random = new Random();
		for (int i = 0; i < arr2.length; i++) {
			arr3[i] = random.nextInt();
		}
		System.out.println("原数组 ：");
		print(arr3);
		findTopN(50, arr3);
		System.out.println("调整后数组：");
		print(arr3);
		
	}
}
