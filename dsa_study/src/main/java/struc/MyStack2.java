package struc;


import java.util.Arrays;

/**
 * @ClassName MyStack
 * @Description 数组实现栈
 * @Author liangxp
 * @Date 2020/6/27 21:30
 **/
public class MyStack2<T> {
    private T[] data;
    private int size;
    private int top;

    public MyStack2(int size){
        this.size = size;
        this.data = (T[]) new Object[size];
        this.top = -1;
    }

    public boolean isNull() {
        boolean resp = this.top == -1 ? true : false;
        return resp;
    }

    public boolean isFull(){
        boolean resp = this.top == this.size - 1 ? true : false;
        return resp;
    }

    public boolean push(T value){
        if (isFull()){
            resize(this.size);
        }
        top++;
        data[top] = value;
        return true;
    }

    public T pop() {
        if (isNull()){
            return null;
        } else {
            T value = data[top];
            --top;
            return value;
        }
    }

    public T[] resize(int size){
        this.size = size * 2;
        this.data = Arrays.copyOf(this.data,this.size);
        return this.data;
    }


    public static void main(String[] args) {
        MyStack2<String> stack = new MyStack2<String>(3);
        stack.push("e");
        stack.push("4");
        stack.push("3");
        stack.push("6");
        System.out.println(stack.isFull());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.isNull());

    }

}
