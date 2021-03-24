package lagou;

/**
 * @ClassName Case06_AvgArr
 * @Description 数组求平均分
 * @Author liangxp
 * @Date 2020/9/29 18:38
 **/
public class Case06_AvgArr {
    public static void main(String[] args) {
        getScore();

    }

    public static void getScore() {
        int max_inx,min_inx,max_val,min_val;
        int inx1,inx2,sumscore;
        double avg;

        int a[] = { 2, 1, 4, 5, 3 };
        max_inx = -1;
        max_val = -1;
        min_inx= -1;
        min_val = 99;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max_val) {
                max_val = a[i];
                max_inx = i;
            }
            if (a[i] < min_val) {
                min_val = a[i];
                min_inx = i;
            }
        }

        inx1 = max_inx;
        inx2 = min_inx;
        if (max_inx < min_inx){
            inx1 = min_inx;
            inx2 = max_inx;
        }
        for (int i = inx1; i < a.length-1; i++) {
            a[i] = a[i+1];
        }
        for (int i = inx2; i < a.length-1; i++) {
            a[i] = a[i+1];
        }
        sumscore = 0;
        for (int i = 0; i < a.length-2; i++) {
            sumscore += a[i];
        }
        avg = sumscore/3.0;
        System.out.println(avg);
    }
}
