package com.diy.framework.annotation;

import java.lang.annotation.*;
/**
 * @ClassName MyController
 * @Description TODO
 * @Author liangxp
 * @Date 2020/7/16 16:42
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyController {

    String value() default "";
}
