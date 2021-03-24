package com.study;

import com.google.common.collect.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName MultiSet
 * @Description Guava新集合
 * @Author liangxp
 * @Date 2021/2/1 13:55
 **/
public class MultiSet {
    public static void main(String[] args) {
        // MultiSet 统计集合元素出现的次数
        // 原有统计的写法
        List<String> words = Lists.newArrayList("a","b","c","b","b","c");

        Map<String, Integer> counts = new HashMap<String, Integer>();
        for (String word : words) {
            Integer count = counts.get(word);
            if (count == null) {
                counts.put(word, 1);
            } else {
                counts.put(word, count + 1);
            }
        }

        // 用了MultiSet后
        HashMultiset<String> multiset = HashMultiset.create();
        for (String word: words) {
            multiset.add(word);
        }
        System.out.println(multiset);
        HashMultiset<String> multiset2 = HashMultiset.create(words);
        multiset2.add("d",4);
        System.out.println(multiset2);
        System.out.println(multiset2.count("c"));
        System.out.println(multiset2.size());
        System.out.println(multiset2.setCount("c",3));
        System.out.println(multiset2.size());
        System.out.println(multiset2.toString());
        multiset2.add("e");
        multiset2.add("f",2);
        System.out.println(multiset2.toString());
        System.out.println("===========SortedMultiset===========");

        // SortedMultiset是Multiset的变体，增加了针对元素次数的排序功能，接口实现类为TreeMultiset
        SortedMultiset<Integer> sortedMultiset = TreeMultiset.create();
        sortedMultiset.add(1,2);
        sortedMultiset.add(2,4);
        sortedMultiset.add(3,6);
        sortedMultiset.add(4,8);
        sortedMultiset.add(5,10);
        System.out.println(sortedMultiset);
        sortedMultiset = sortedMultiset.descendingMultiset();
        System.out.println(sortedMultiset);
        System.out.println(sortedMultiset.firstEntry().getElement());

        // lowerBound代表集合下界 upperBound代表集合上界 OPEN代表不包含集合边界 CLOSED代表包含集合边界
        SortedMultiset<Integer> sortedMultiset2 = sortedMultiset.subMultiset(4, BoundType.OPEN,2,BoundType.OPEN);
        System.out.println(sortedMultiset2);
        sortedMultiset2 = sortedMultiset.subMultiset(4, BoundType.CLOSED,2,BoundType.CLOSED);
        System.out.println(sortedMultiset2);
        SortedMultiset<Integer> sortedMultiset3 = sortedMultiset.headMultiset(1, BoundType.OPEN).tailMultiset(5, BoundType.OPEN);
        SortedMultiset<Integer> sortedMultiset4 = sortedMultiset.headMultiset(1, BoundType.CLOSED).tailMultiset(5, BoundType.CLOSED);
        System.out.println(sortedMultiset3);
        System.out.println(sortedMultiset4);


        Set<String> wordsWithPrimeLength = ImmutableSet.of("one", "two", "three", "six", "seven", "eight");
        Set<String> primes = ImmutableSet.of("two", "three", "five", "seven");
        Sets.SetView<String> intersection = Sets.intersection(primes, wordsWithPrimeLength);
        System.out.println(intersection);
        Sets.SetView<String> union = Sets.union(wordsWithPrimeLength, primes);
        System.out.println(union);
        Sets.SetView<String> difference = Sets.difference(wordsWithPrimeLength, primes);
        System.out.println(difference);
        Sets.SetView<String> symmetricDifference = Sets.symmetricDifference(wordsWithPrimeLength, primes);
        System.out.println(symmetricDifference);


    }
}
