package struc;


/**
 * @ClassName MyStack
 * @Description 数组实现栈
 * @Author liangxp
 * @Date 2020/6/27 21:30
 **/
public class MyStack<T> {
    private String[] data;
    private int size;
    private int top;

    public MyStack(int size){
        this.size = size;
        this.data = new String[size];
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

    public boolean push(String value){
        if (isFull()){
            return false;
        } else {
            top++;
            data[top] = value;
            return true;
        }
    }

    public String pop() {
        if (isNull()){
            return null;
        } else {
            String value = data[top];
            --top;
            return value;
        }
    }


    public static void main(String[] args) {
        MyStack<String> stack = new MyStack<String>(3);
        stack.push("e");
        stack.push("3");
        stack.push("3");
        System.out.println(stack.isFull());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.isNull());

    }




}
