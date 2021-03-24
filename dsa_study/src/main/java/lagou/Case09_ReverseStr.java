package lagou;

import java.util.Stack;

/**
 * @ClassName Case09_ReverseStr
 * @Description 反转字符串中的单词
 * @Author liangxp
 * @Date 2020/7/14 11:35
 **/
public class Case09_ReverseStr {
    public static void main(String[] args) {
        String words = "This is a  good example";
        System.out.println(reverseWords(words));

    }

    private static String reverseWords(String words) {
        Stack stack = new Stack();
        String temp = "";
        for (int i = 0; i < words.length(); i++) {
            if (words.charAt(i) != ' '){
                temp += words.charAt(i);
            } else if(temp != ""){
                stack.push(temp);
                temp = "";
            }else {
                continue;
            }
        }
        if (temp != ""){
            stack.push(temp);
        }

        String result = "";
        while (!stack.isEmpty()){
            result += stack.pop() + " ";
        }
        return result.substring(0,result.length()-1);
    }
}
