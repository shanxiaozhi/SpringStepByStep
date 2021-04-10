package com.yc.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Component
public class HelloWorld {
    @PostConstruct
    public void  setup(){

        System.out.println("容器构造后的setup");
    }

    @PreDestroy
    public void destroy(){

        System.out.println("容器销hui前");
    }

    public  HelloWorld(){
        System.out.println(" hello world 构造");
    }

    public void  show(){
        System.out.println("show");
    }

}
