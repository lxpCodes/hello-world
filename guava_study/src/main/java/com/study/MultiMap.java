package com.study;

import com.google.common.collect.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MultiMap
 * @Description MultiMap新集合
 * @Author liangxp
 * @Date 2021/2/1 14:46
 **/
public class MultiMap {
    public static void main(String[] args) {
        System.out.println("=======listMultimap=======");
        ListMultimap<String,Integer> listMultimap = MultimapBuilder
                .treeKeys()
                .arrayListValues()
                .build();
        listMultimap.put("1",1);
        listMultimap.put("1",2);
        listMultimap.put("2",3);
        listMultimap.put("2",4);
        System.out.println(listMultimap);

        List<Integer> value = listMultimap.get("1");
        value.add(3);
        System.out.println(listMultimap);

        listMultimap.removeAll("2");
        System.out.println(listMultimap);
        listMultimap.remove("1",1);
        System.out.println(listMultimap);

        Map<String, Collection<Integer>> mapView = listMultimap.asMap();
        System.out.println(mapView);

        System.out.println("=======SetMultimap=======");
        SetMultimap<Comparable, Object> setMultimap = MultimapBuilder.treeKeys().hashSetValues().build();
        setMultimap.put("a",2);
        setMultimap.put("a",3);
        System.out.println(setMultimap);

        System.out.println("=========BiMap==========");
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("liangxp","programmer");
        biMap.put("liangxp2","programmer2");
        System.out.println(biMap.get("liangxp"));
        // inverse反转时value不能重复,否则报错
        System.out.println(biMap.inverse().get("programmer"));
        System.out.println(biMap.inverse().get("programmer2"));

        System.out.println("=========Table================");
        Table<String, String, String> basedTable = HashBasedTable.create();
        basedTable.put("male","programmer","liangxp");
        basedTable.put("female","beauty","likx");
        basedTable.put("female","programmer","wangxf");
        System.out.println(basedTable.get("male","programmer"));
        System.out.println(basedTable.get("female","programmer"));
        System.out.println(basedTable.row("male").get("programmer"));
        System.out.println(basedTable.column("programmer").get("female"));


    }
}
