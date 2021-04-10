package com.yc.bean;

import com.yc.springFramework.stereotype.MyComponent;
import com.yc.springFramework.stereotype.MyRepository;
import com.yc.springFramework.stereotype.MyResource;
/**
 * @author liyan
 * @create 2021-04-2021/4/5-20:39
 */
@MyRepository
public class HelloWorldBiz {
    public HelloWorld helloWorld;

    @MyResource(name = "helloWorld1")
    public void setHelloWorld(HelloWorld helloWorld) {
        this.helloWorld = helloWorld;
    }
}
