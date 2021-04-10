package com.yc.springFramework.stereotype;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author liyan
 * @create 2021-04-2021/4/5-11:29
 */
@Target({TYPE, FIELD, METHOD})
@Retention(RUNTIME)
public @interface MyResource {
    String name() default "";
    Class<?> type() default java.lang.Object.class;
}
