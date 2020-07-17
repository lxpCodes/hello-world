package arith;

import java.util.Arrays;

/**
 * @ClassName CompareStr
 * @Description 比较字符串是否相等
 *              如果认为aab与aba是相等的，则给两组字符串比较是否相等
 * @Author liangxp
 * @Date 2020/7/16 15:58
 **/
public class CompareStr {

    public static void main(String[] args) {
        String str1 = "dasdada";
        String str2 = "ddaasad";
        System.out.println(compareStr(str1,str2));;
        System.out.println(compareStr2(str1,str2));;
        System.out.println(compareStr3(str1,str2));;
    }

    private static boolean compareStr3(String str1, String str2) {
        if (str1.length() != str2.length()){
            return false;
        }
        if (str1 == str2) {
            return true;
        }
        if (str1.equals(str2)) {
            return true;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        return Arrays.equals(chars1,chars2);
    }

    private static boolean compareStr2(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        StringBuffer stringBuffer = new StringBuffer(str2);
        for (int i = 0; i < str1.length(); i++) {
            char temp = str1.charAt(i);
            int index = stringBuffer.toString().indexOf(temp);
            if (index != -1){
                stringBuffer.deleteCharAt(index);
            } else {
                return false;
            }
        }
        if (stringBuffer.toString().length() == 0) {
            return true;
        }
        return false;
    }

    private static boolean compareStr(String str1, String str2) {
        byte[] bytes1 = str1.getBytes();
        byte[] bytes2 = str2.getBytes();
        Arrays.sort(bytes1);
        Arrays.sort(bytes2);
        return Arrays.equals(bytes1,bytes2);
    }
}
