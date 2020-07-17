package com.diy.framework.annotation;

import java.lang.annotation.*;

/**
 * @ClassName MyService
 * @Description TODO
 * @Author liangxp
 * @Date 2020/7/16 16:44
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyService {

    String value() default "";
}
