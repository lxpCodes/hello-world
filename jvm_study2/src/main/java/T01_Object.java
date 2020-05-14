import org.openjdk.jol.info.ClassLayout;

/**
 * @ClassName T01_Object
 * @Description 对象布局测试
 * @Author liangxp
 * @Date 2020/5/13 18:18
 **/
public class T01_Object {
    public static void main(String[] args) {

        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        System.out.println("=================");

        int[] i = {1, 2, 3};
        System.out.println(ClassLayout.parseInstance(i).toPrintable());


    }
}
