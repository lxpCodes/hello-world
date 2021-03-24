package com.study;

import com.google.common.collect.*;

import java.util.*;

/**
 * @ClassName ImmutableCollec
 * @Description 不可变集合
 * @Author liangxp
 * @Date 2021/2/1 10:00
 **/
public class ImmutableCollec {
    public static void main(String[] args) {
        // 普通的Collection集合创建对比
        List<String> list = Lists.newArrayList();
        Set<String> set = Sets.newHashSet();
        Map<String, String> map = Maps.newHashMap();
        // 不可变对象创建,特点在于
        // 1.在多线程操作下,是线程安全的
        // 2.所有不可变集合会比可变集合更有效的利用资源
        // 3.中途不可改变
        ImmutableList<String> iList = ImmutableList.of("a", "b", "c");
        ImmutableSet<String> iSet = ImmutableSet.of("e1", "e2");
        ImmutableMap<String, String> iMap = ImmutableMap.of("k1", "v1", "k2", "v2");
        System.out.println(iList);
        System.out.println(iSet);
        System.out.println(iMap);

        // Map-List对比
        Map<String,List<Integer>> map2 = new HashMap<String,List<Integer>>();
        List<Integer> list2 = new ArrayList<Integer>();
        list2.add(1);
        list2.add(2);
        map2.put("test", list2);
        System.out.println(map2.get("test"));

        // Guava写法
        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("test",1);
        multimap.put("test",2);
        System.out.println(multimap.get("test"));


    }
}
