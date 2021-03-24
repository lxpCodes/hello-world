package com.study;

import java.util.Optional;

/**
 * @ClassName OptionalTest
 * @Description 避免使用null
 * @Author liangxp
 * @Date 2021/2/3 14:44
 **/
public class OptionalTest {
    public static void main(String[] args) {
        Optional<Integer> optional = Optional.of(5);
        boolean present = optional.isPresent();
        System.out.println(present);
        System.out.println(optional.get());

        Optional<Object> optional2 = Optional.ofNullable("test");
        System.out.println(optional2.isPresent());
    }
}
