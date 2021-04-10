package com.yc.annotation;

import java.lang.annotation.*;

/**
 * @author liyan
 * @create 2021-03-2021/3/29-20:33
 */
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.CLASS)
@Documented
@Inherited
public @interface MyHelloWord {
}
