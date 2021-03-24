package com.study;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @ClassName StringJoiner
 * @Description 字符串连接器
 * @Author liangxp
 * @Date 2021/2/1 10:35
 **/
public class StringJoiner {

    public static void main(String[] args) {
        // 字符串连接器,以|为分隔符,同时去除null元素
        StringBuilder stringBuilder = new StringBuilder("嗨,");
        Joiner joiner = Joiner.on("|").skipNulls();
        // 构成一个字符串jim|jack|kevin并添加到stringBuilder
        stringBuilder = joiner.appendTo(stringBuilder,"jim", "jack", null, "kevin");
        System.out.println(stringBuilder);

        // 将Map转换为字符串
        Map<String, String> testMap = Maps.newLinkedHashMap();
        testMap.put("Cookies", "12332");
        testMap.put("Content-Length","30000");
        testMap.put("Date","2018.07.04");
        testMap.put("Mime", "text/html");

        // 用:分割键值对，并用#分割每个元素，返回字符串
        String result = Joiner.on("&").withKeyValueSeparator("=").join(testMap);
        System.out.println(result);

        // 将字符串转化为Map  接上一个，内部类的引用，得到分割器，将字符串解析为map
        Splitter.MapSplitter ms = Splitter.on("&").withKeyValueSeparator('=');
        Map<String, String> ret = ms.split(result);
        for (String key: ret.keySet()) {
            System.out.println(key + " -> " + ret.get(key));
        }
        System.out.println("================");
        Splitter.on(",").omitEmptyStrings().splitToList("123,456,789,23").forEach(System.out::println);
        Splitter.on(",").limit(2).splitToList("123,456,789,,23").forEach(System.out::println);
        Splitter.on(",").trimResults().splitToList("12 3, 456 ,789,,23").forEach(System.out::println);
        Map<String,String> map = Splitter.on(",").withKeyValueSeparator("-").split("1-2,3-5");
        System.out.println(map);

    }
}
