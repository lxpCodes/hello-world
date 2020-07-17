package com.diy.framework.annotation;

import java.lang.annotation.*;

/**
 * @ClassName MyAutowired
 * @Description TODO
 * @Author liangxp
 * @Date 2020/7/16 16:39
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAutowired {
    String value() default "";
}
