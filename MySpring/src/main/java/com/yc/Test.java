package com.yc;

import com.yc.bean.HelloWorld;
import com.yc.bean.HelloWorldBiz;
import com.yc.springFramework.context.MyAnnationConfigApplicationContext;
import com.yc.springFramework.context.MyApplicationContext;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author liyan
 * @create 2021-04-2021/4/5-11:58
 */
public class Test {
    public static void main(String[] args) throws IllegalAccessException, IOException, InstantiationException, InvocationTargetException {
        MyApplicationContext ac=new MyAnnationConfigApplicationContext(MyAppConfig.class);
        HelloWorldBiz HelloWorldBiz= (HelloWorldBiz) ac.getBean(HelloWorldBiz.class);
        System.out.println(HelloWorldBiz.helloWorld.name);

    }
}
