package multi.thread.test;

/**
 * @ClassName M
 * @Description TODO
 * @Author liangxp
 * @Date 2020/6/28 16:30
 **/
public class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
