package com.yc;

/**
 * @author liyan
 * @create 2021-03-2021/3/29-19:19
 */
public class Person implements Showable {
    private String name;
    private Integer age;
    public  void Person (){
        System.out.println("这是Person的无参构造方法");
    }
    public  void Person (String name){
        this.name=name;
        System.out.println("这是Person的有参构造方法");
    }

    @Override
    public void show() {
        System.out.println("名字："+name+"/d年龄："+age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "姓名："+name+"/d年龄:"+age;
    }
}
