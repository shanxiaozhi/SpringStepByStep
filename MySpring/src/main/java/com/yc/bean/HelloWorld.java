package com.yc.bean;

import com.yc.springFramework.stereotype.MyComponent;
import com.yc.springFramework.stereotype.MyPostConstruct;

/**
 * @author liyan
 * @create 2021-04-2021/4/5-11:59
 */
//@MyComponent
public class HelloWorld {
    public String name="liyan";
    public HelloWorld() {
        System.out.println("调用无参构造函数");
    }
    @MyPostConstruct
    public void postconstruct(){
        System.out.println("调用postconstruct方法");
    }
}
