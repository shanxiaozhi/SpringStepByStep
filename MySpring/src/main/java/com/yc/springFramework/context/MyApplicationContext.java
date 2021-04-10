package com.yc.springFramework.context;

/**
 * @author liyan
 * @create 2021-04-2021/4/5-11:42
 */
public interface MyApplicationContext {

    public Object getBean(Class o);
    public Object getBean(String id);
}
