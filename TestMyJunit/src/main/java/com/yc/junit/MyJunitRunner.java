package com.yc.junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liyan
 * @create 2021-03-2021/3/31-19:54
 */
public class MyJunitRunner {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class cls=Class.forName("com.yc.MyCalculatorTest");
        Method[] methods = cls.getMethods();
        List<Method> map=new ArrayList<>();
        Object o=cls.newInstance();
        Method beforclass=null;
        Method afterclass=null;
        Method befor=null;
        Method after=null;
        for(Method m:methods){

            if(m.isAnnotationPresent(MyTest.class) ){
                map.add(m);
            }else if(m.isAnnotationPresent(MyBeforeClass.class) ){
                beforclass=m;
            }else if(m.isAnnotationPresent(MyAfterClass.class) ){
                afterclass=m;
            }else if(m.isAnnotationPresent(MyBefore.class) ){
                befor=m;
            }else if(m.isAnnotationPresent(MyAfter.class) ){
                after=m;
            }

        }
        if(beforclass!=null){
            beforclass.invoke(o,null);
        }

        for(Method m:map){
            if(befor!=null){
                befor.invoke(o,null);
            }
            m.invoke(o,null);
            if(after!=null){
                after.invoke(o,null);
            }
        }

        if(afterclass!=null){
            afterclass.invoke(o,null);
        }

    }

}
