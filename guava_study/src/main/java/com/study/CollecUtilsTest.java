package com.study;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CollecUtilsTest
 * @Description 集合工具类
 * @Author liangxp
 * @Date 2021/2/1 15:29
 **/
public class CollecUtilsTest {
    public static void main(String[] args) {
        List<String> test = new ArrayList<>();
        List<String> test2 = Lists.newArrayList();
        List<String> test3 = Lists.newArrayList("1","2");
        List<String> test4 = Lists.newArrayList(test3);
        System.out.println(test3);
        System.out.println(test4);

    }
}
