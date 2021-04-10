package com.yc.springFramework.stereotype;

import java.lang.annotation.*;

/**
 * @author liyan
 * @create 2021-04-2021/4/5-11:31
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent
public @interface MyController {
    String value() default "";
}