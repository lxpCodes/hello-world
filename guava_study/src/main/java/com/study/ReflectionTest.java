package com.study;

import com.google.common.reflect.TypeToken;

/**
 * @ClassName ReflectionTest
 * @Description 反射工具类
 * @Author liangxp
 * @Date 2021/2/1 20:01
 **/
public class ReflectionTest {
    public static void main(String[] args) {
        TypeToken<String> stringTok = TypeToken.of(String.class);
        TypeToken<Integer> intTok = TypeToken.of(Integer.class);
        System.out.println(stringTok.getRawType().getName());
        System.out.println(intTok.getRawType().getName());
    }
}
