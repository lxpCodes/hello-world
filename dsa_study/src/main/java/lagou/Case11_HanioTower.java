package lagou;

/**
 * @ClassName Case11_HanioTower
 * @Description 递归完成汉诺塔
 * @Author liangxp
 * @Date 2020/7/14 10:08
 **/
public class Case11_HanioTower {
    public static void main(String[] args) {
        //三根柱子标记为x y z  待移动的盘子数量n
        // 移动步骤
        //1、把从小到大的n-1个盘子从x移动到y，即hanio(n-1,x,z,y)
        //2、再把最大的盘子从x移动到z，完成一次移动动作
        //3、把从小到大的n-1个盘子从y移动到z,hanio(n-1,y,x,z)
        // n=1，终止
        String x = "x";
        String y = "y";
        String z = "z";
        hanio(3,x,y,z);


    }

    private static void hanio(int n, String x, String y, String z) {
        if (n < 1) {
            System.out.println("汉诺塔层数不能小于1");
        } else if(n == 1){
            System.out.println("移动:" + x + "-->" + z);
        } else {
            hanio(n-1,x,z,y);
            System.out.println("移动:" + x + "-->" + z);
            hanio(n-1,y,x,z);
        }
    }
}
