package com.diy.framework.annotation;

import java.lang.annotation.*;

/**
 * @ClassName MyRequestMapping
 * @Description TODO
 * @Author liangxp
 * @Date 2020/7/16 16:43
 **/
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestMapping {

    String value() default "";
}
