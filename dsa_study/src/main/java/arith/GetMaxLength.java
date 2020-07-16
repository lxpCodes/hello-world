package arith;

/**
 * @ClassName GetMaxLength
 * @Description 在一个空格分隔的字符串中，求最长子串长度，时间空间复杂度尽可能优化
 * @Author liangxp
 * @Date 2020/7/16 10:45
 **/
public class GetMaxLength {
    public static void main(String[] args) {
        String orignStr = "a dd ddasaa ssdc d dd ssaa s";

        System.out.println(getMaxLen1(orignStr));;

        System.out.println(getMaxLen2(orignStr));
    }

    private static int getMaxLen2(String orignStr) {
        int len = 0,i = 0, j = 0;
        while (j < orignStr.length()){
            if (orignStr.charAt(j) == ' '){
                if ((j - i) > len){
                    len = j - i;
                }
                while (j < orignStr.length()){
                    j++;
                    if (orignStr.charAt(j) != ' '){
                        i = j;
                        break;
                    }
                }
            }
            j ++;
        }
        return len;
    }

    private static int getMaxLen1(String orignStr) {
        String[] strs = orignStr.split(" ");
        int len = 0;
        for (String str: strs) {
            if (str.length() > len) {
                len = str.length();
            }
        }
        return len;
    }

}
