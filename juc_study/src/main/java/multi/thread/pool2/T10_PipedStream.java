package multi.thread.pool2;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @ClassName T10_PipedStream
 * @Description 根据PipedStream实现交替打印
 * @Author liangxp
 * @Date 2021/4/23 15:06
 **/
public class T10_PipedStream {
    public static void main(String[] args) throws Exception{
        char[] c1 = "1234567".toCharArray();
        char[] c2 = "ABCDEFG".toCharArray();

        PipedInputStream input1 = new PipedInputStream();
        PipedInputStream input2 = new PipedInputStream();
        PipedOutputStream output1 = new PipedOutputStream();
        PipedOutputStream output2 = new PipedOutputStream();


        input1.connect(output2);
        input2.connect(output1);


        String msq = "Your Turn";

        new Thread(() ->{
            byte[] buffer = new byte[9];

            try {
                for (char c : c1) {
                    input1.read(buffer);
                    if (new String(buffer).equals(msq)){
                        System.out.print(c);
                    }
                    output1.write(msq.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        },"t1").start();


        new Thread(() ->{
            byte[] buffer = new byte[9];

            try {
                for (char c : c2) {
                    System.out.print(c);
                    output2.write(msq.getBytes());
                    input2.read(buffer);
                    if (new String(buffer).equals(msq)){
                        continue;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        },"t2").start();

    }
}
