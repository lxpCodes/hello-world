package leetcode;

/**
 * @ClassName Case0003_find_longest_str
 * @Description 获取字符串中不重复的最长子串长度
 * @Author liangxp
 * @Date 2020/12/31 15:44
 **/
public class Case0003_find_longest_str {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwdkeadcgrmlj"));
    }

    public static int lengthOfLongestSubstring(String s) {
        // 设置左右两个指针表示滑动窗口
        int left = 0;
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            // 滑动窗口的右指针是当前下标+1
            int right = i + 1;
            String subStringLeft = s.substring(left, i);
            String currentChar = s.substring(i, i + 1);
            // 如果滑动窗口内有重复元素就更新左坐标
            while (subStringLeft.contains(currentChar)) {
                left++;
                subStringLeft = s.substring(left, i);
            }
            String subString = s.substring(left, right);
            maxLength = Math.max(maxLength, subString.length());
        }
        return maxLength;
    }
}
