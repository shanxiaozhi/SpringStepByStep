package com.yc.bean;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author liyan
 * @create 2021-04-2021/4/4-14:43
 */
@Component
@Lazy
public class HelloWorld {
    public String name="hello";

    public HelloWorld() {
        System.out.println("无参构造");
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
