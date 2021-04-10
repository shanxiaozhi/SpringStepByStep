package com.yc.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liyan
 * @create 2021-03-2021/3/29-21:08
 */
@Target(value = {ElementType.TYPE,ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DbConnection {
    //连接数据库的属性
    public String url() default "";
    public String driverClass() default "";
    public String user() default "root";
    public String passWord() default "a";
}
