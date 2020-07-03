package multi.thread.test;

import java.io.IOException;

/**
 * @ClassName T01_NormalReference
 * @Description 强引用演示
 * @Author liangxp
 * @Date 2020/6/28 16:32
 **/
public class T01_NormalReference {
    public static void main(String[] args) throws IOException {
        M m = new M();
        //m = null;
        System.gc();
        System.in.read();
    }
}
