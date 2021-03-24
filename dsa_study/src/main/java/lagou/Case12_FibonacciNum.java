package lagou;

/**
 * @ClassName Case12_FibonacciNum
 * @Description 获取斐波那契数列
 * @Author liangxp
 * @Date 2020/7/14 11:23
 **/
public class Case12_FibonacciNum {
    public static void main(String[] args) {
        int x = 20;
        for (int i = 1; i < x; i++) {
            System.out.println(fibonacc(i));
        }
    }

    private static int fibonacc(int n){
        if (n == 1){
            return 0;
        }
        if (n == 2){
           return 1;
        }
        int num = fibonacc(n - 2) + fibonacc(n - 1);
        return num;
    }
}
