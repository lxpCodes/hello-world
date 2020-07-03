import org.openjdk.jol.info.ClassLayout;

/**
 * @ClassName T02_Synchronize
 * @Description TODO
 * @Author liangxp
 * @Date 2020/5/18 9:51
 **/
public class T02_Synchronize {

    public static void main(String[] args) {

        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

    }
}
