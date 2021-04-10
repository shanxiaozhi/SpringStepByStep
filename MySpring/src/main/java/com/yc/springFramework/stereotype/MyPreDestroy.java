package com.yc.springFramework.stereotype;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author liyan
 * @create 2021-04-2021/4/5-18:26
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface MyPreDestroy {

}
