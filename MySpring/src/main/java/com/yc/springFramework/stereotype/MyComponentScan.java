package com.yc.springFramework.stereotype;

import java.lang.annotation.*;

/**
 * @author liyan
 * @create 2021-04-2021/4/5-11:36
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface MyComponentScan {
    String[] basePackages() default {};
}
