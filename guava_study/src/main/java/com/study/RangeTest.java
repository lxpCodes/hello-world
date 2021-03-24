package com.study;


import com.google.common.collect.Range;
import com.google.common.primitives.Ints;

/**
 * @ClassName RangeTest
 * @Description 区间测试类
 * @Author liangxp
 * @Date 2021/2/3 15:50
 **/
public class RangeTest {
    public static void main(String[] args) {
        System.out.println("========区间运算=======");
        System.out.println(Range.closed(1,3).contains(2));
        System.out.println(Range.closed(1,3).contains(4));
        System.out.println(Range.lessThan(5).contains(5));
        System.out.println(Range.closed(1,4).containsAll(Ints.asList(1,2,3)));

        System.out.println("========查询运算=======");
        System.out.println(Range.closedOpen(4,4).isEmpty());
        System.out.println(Range.openClosed(4,4).isEmpty());
        System.out.println(Range.closed(4,4).isEmpty());
//        System.out.println(Range.open(4,4).isEmpty());
        System.out.println(Range.closed(3,10).lowerEndpoint());
        System.out.println(Range.open(3,10).lowerBoundType());
        System.out.println(Range.open(3,10).upperBoundType());

        System.out.println("=======关系运算=======");
        System.out.println(Range.closed(3,5).isConnected(Range.open(5,10)));
        System.out.println(Range.closed(0,9).isConnected(Range.closed(3,4)));
        System.out.println(Range.closed(0,5).isConnected(Range.closed(3,9)));
        System.out.println(Range.open(3,5).isConnected(Range.open(5,10)));
        System.out.println(Range.closed(1,5).isConnected(Range.closed(6,10)));


    }
}
