package com.yc.annotation;

/**
 * @author liyan
 * @create 2021-03-2021/3/29-20:55
 */
@MyHelloWord
public class Teacher {
    private  String name;
    @MyHelloWord
    private String show(@MyHelloWord String s){
        System.out.println("I am a "+s);
        return "I am a "+s;
    }
}
