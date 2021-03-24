package leetcode;

/**
 * @ClassName Case0005_find_max_hwstr
 * @Description 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000
 * @Author liangxp
 * @Date 2021/1/6 10:52
 **/
public class Case0005_find_max_hwstr {

    public static void main(String[] args) {

        System.out.println(longestPalindrome("mhgjgho"));
//        System.out.println(longestPalindrome2("dlkmhgjghopeq"));
//        System.out.println(longestPalindrome3("dlkmhgjghopeq"));
    }

    // 动态规划
    public static String longestPalindrome3(String str) {
        int len = str.length();
        if (len < 2){
            return str;
        }

        int begin = 0;
        int maxlen = 1;

        //dp[i][j] 表示s[i..j]是否为回文串
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            // 对角线肯定true
            dp[i][i] = true;
        }

        char[] chars = str.toCharArray();
        // 一列一列的填充
        for (int lie = 1; lie < len; lie++) {
            for (int hang = 0; hang < lie; hang++) {
                if (chars[hang] != chars[lie]) {
                    dp[hang][lie] = false;
                } else {
                    if (lie - hang < 3) {
                        dp[hang][lie] = true;
                    } else {
                        // 动态转移方程
                        dp[hang][lie] = dp[hang+1][lie-1];
                    }
                }

                // 只要dp[i][j] == true,表示子串s[i..j]是回文，此时记录回文长度和起始位置
                if (dp[hang][lie] && lie- hang + 1 > maxlen){
                    maxlen = lie - hang + 1;
                    begin = hang;
                }
            }
        }
        return str.substring(begin,begin + maxlen);
    }


    // 中心扩散法
    public static String longestPalindrome2(String str){
        int len = str.length();
        if (len < 2){
            return str;
        }

        int begin = 0;
        int maxlen = 1;

        char[] chars = str.toCharArray();
        for (int i = 0; i < len - 1; i++) {
            int oddLen = expandFromCenter(chars,i,i);
            int evenLen = expandFromCenter(chars,i,i+1);

            int currMaxLen = Math.max(oddLen,evenLen);
            if (currMaxLen > maxlen){
                maxlen = currMaxLen;
                // -1是为了整合回文串长度分别为奇偶时
                begin = i - (maxlen -1) / 2;
            }
        }

        return str.substring(begin,begin+maxlen);
    }

    /**
     * @description 回文串的长度
     *              原始字符串的字符数组
     *              起始左边界
     *              起始右边界
     * @date 2021/1/6 13:08
     */
    private static int expandFromCenter(char[] chars, int left, int right) {
        // left = right 时，回文中心是一个字符，回文串长度是奇数
        // right = left + 1 时，回文中心是两个字符，回文串长度是偶数
        int len = chars.length;
        while (left >= 0 && right < len){
            if (chars[left] == chars[right]) {
                left --;
                right ++;
            } else {
                break;
            }
        }
        // char[left] != char[right] 时跳出循环
        // 回文长度等于right - left + 1 -2 =  right - left - 1
        return right - left - 1;
    }

    // 暴力解法
    public static String longestPalindrome(String str){
        int len = str.length();
        if (len < 2){
            return str;
        }

        int begin = 0;
        int maxlen = 1;

        char[] chars = str.toCharArray();

        for (int i = 0; i < len -1; i++) {
            for (int j = i + 1; j < len; j++) {
                // j - i + 1 获取回文串长度,validPalindromic 校验是否为回文串
                if (j - i + 1 > maxlen && validPalindromic(chars,i,j)){
                    begin = i;
                    maxlen = j - i + 1;
                }
            }
        }
        return str.substring(begin,begin+maxlen);
    }

    private static boolean validPalindromic(char[] chars, int left, int right) {
        System.out.println("组合情况:left:" + left + ",right:" + right);
        while (left != right){
            if (chars[left] != chars[right]){
                return false;
            }
            left ++;
            right --;
        }
        return true;
    }
}
