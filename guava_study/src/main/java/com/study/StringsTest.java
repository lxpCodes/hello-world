package com.study;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;

/**
 * @ClassName StringsTest
 * @Description 字符串工具类
 * @Author liangxp
 * @Date 2021/2/1 10:54
 **/
public class StringsTest {
    public static void main(String[] args) {
        System.out.println(Strings.isNullOrEmpty(""));
        System.out.println(Strings.isNullOrEmpty(null));
        System.out.println(Strings.isNullOrEmpty("hello"));
        // 将null化为""
        System.out.println(Strings.nullToEmpty(null));

        //从尾部不断补充T 直到总共8个字符，如果源字符串已经达到或操作，则原样返回。类似的有padStart
        System.out.println(Strings.padEnd("hello",8,'T'));
        System.out.println(Strings.padStart("hello",8,'T'));

        // 空白回车换行对应换成一个#，一对一换
        String stringWithLineBreaks = "hello world\r\r\ryou are here\n\ntake it\t\t\teasy";
        String replace = CharMatcher.breakingWhitespace().replaceFrom(stringWithLineBreaks, '#');
        System.out.println(replace);

        // 连续空白缩成一个字符  将所有连在一起的空白回车换行字符换成一个#
        String  taBString = "  hello  \n\tworld   you\rare                here  ";
        String collapse = CharMatcher.whitespace().collapseFrom(taBString, '#');
        System.out.println(collapse);

        // 去掉前后空白和缩成一个字符  在前面的基础上去掉字符串的前后空白，并将空白换成一个#
        String trimRet = CharMatcher.whitespace().trimAndCollapseFrom(taBString, '#');
        System.out.println(trimRet);
        System.out.println("========================");
        // 保留数字
        System.out.println(CharMatcher.inRange('0','9').retainFrom("asfds12312fds444"));
        //12312444
        System.out.println(CharMatcher.inRange('0','9').removeFrom("asfds12312fds444"));
        //asfdsfds
        System.out.println(CharMatcher.inRange('0','9').or(CharMatcher.whitespace()).retainFrom("as fds123 12 fds444"));
        // 123 12 444


        byte[] bytes = "梁修鹏".getBytes(Charsets.UTF_8);
        System.out.println(bytes);

        String constant_name = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME");
        System.out.println(constant_name);
    }
}
