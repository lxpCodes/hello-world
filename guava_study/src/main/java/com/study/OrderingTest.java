package com.study;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * @ClassName OrderingTest
 * @Description 比较器测试
 * @Author liangxp
 * @Date 2021/2/3 15:06
 **/
public class OrderingTest {
    public static void main(String[] args) {
        Ordering<String> byLength = new Ordering<String>() {
            @Override
            public int compare(@Nullable String left, @Nullable String right) {
                return Ints.compare(left.length(),right.length());
            }
        };
        // 翻转排序
        Ordering<String> reverse = byLength.reverse();


        Ordering.natural().nullsFirst().onResultOf(new Function<Foo, String>() {
            public String apply(Foo foo){
                return foo.sortedBy;
            }
        });



    }
}

class Foo{
    @Nullable String sortedBy;
    int noSortedBy;
}
