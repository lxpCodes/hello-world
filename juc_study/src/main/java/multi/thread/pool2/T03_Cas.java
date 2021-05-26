package multi.thread.pool2;

/**
 * @ClassName T03_Cas
 * @Description CAS自旋实现交替打印
 * @Author liangxp
 * @Date 2021/4/23 14:45
 **/
public class T03_Cas {

    enum ReadyToRun{T1,T2}

    static volatile ReadyToRun r = ReadyToRun.T1;

    public static void main(String[] args) {
        char[] c1 = "1234567".toCharArray();
        char[] c2 = "ABCDEFG".toCharArray();

        new Thread(() ->{
            for (char c : c1) {
                while (r != ReadyToRun.T1){}
                System.out.print(c);
                r = ReadyToRun.T2;
            }
        },"t1").start();

        new Thread(() ->{
            for (char c : c2) {
                while (r != ReadyToRun.T2){}
                System.out.print(c);
                r = ReadyToRun.T1;
            }
        },"t2").start();
    }
}
