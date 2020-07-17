package com.diy.framework.annotation;

import java.lang.annotation.*;

/**
 * @ClassName MyRequestParam
 * @Description TODO
 * @Author liangxp
 * @Date 2020/7/16 16:46
 **/
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestParam {
    String value() default "";
}
