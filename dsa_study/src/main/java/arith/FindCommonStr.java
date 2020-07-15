package arith;

/**
 * @ClassName FindCommonStr
 * @Description 查找最大公共子串
 * @Author liangxp
 * @Date 2020/7/9 9:28
 **/
public class FindCommonStr {
    public static void main(String[] args) {
        String a = "13452439";
        String b = "123456";
        System.out.println(getCommonStr(a,b));

        System.out.println(getCommonStr3(a,b));

        String s1 = "goodgoogle";
        String s2 = "google";
        getCommonStr2(s1,s2);


    }

    private static String getCommonStr3(String a, String b) {
        String maxStr = "";
        int max_len = 0;
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j)){
                    for (int m = i,n = j; m < a.length() && n < b.length(); m++,n++) {
                        if (a.charAt(m) != b.charAt(n)) {
                            break;
                        }
                        if (max_len < m - i + 1){
                            max_len = m -i + 1;
                            maxStr = a.substring(i,m+1);
                        }
                    }

                }
            }
        }
        return maxStr;
    }

    private static void getCommonStr2(String s1, String s2) {
        int isfind = 0;
        for (int i = 0; i < s1.length() - s2.length() + 1; i++) {
            if (s1.charAt(i) == s2.charAt(0)){
                int jc = 0;
                for (int j = 0; j < s2.length(); j++) {
                    if (s1.charAt(i+j) != s2.charAt(j)) {
                        break;
                    }
                    jc = j;
                }
                if (jc == s2.length() - 1){
                    isfind = 1;
                }
            }
        }
        System.out.println(isfind);
    }

    private static String getCommonStr(String a, String b) {
        char[] c1 = a.toCharArray();
        char[] c2 = b.toCharArray();
        int[][] m = new int[c2.length+1][c1.length+1];
        for (int i = 1; i < c2.length; i++) {
            for (int j = 1; j < c1.length; j++) {
                if (c2[i-1] == c1[j-1]){
                    m[i][j] = m[i-1][j-1] + 1;
                }
            }
        }

        int max = 0;
        int index = 0;
        for (int i = 0; i < c2.length; i++) {
            for (int j = 0; j < c1.length; j++) {
                if (m[i][j] > max){
                    max = m[i][j];
                    index = i;
                }
            }
        }

        //计算得到相同字符起始下标，因为是相同一个就加1，可得下面公式
        String resp = "";
        for (int i = index - max; i < index; i++) {
            resp += b.charAt(i);
        }
        return resp;
    }
}
