package com.yc;

import com.sun.deploy.Environment;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author liyan
 * @create 2021-03-2021/3/29-18:39
 */
public class Test1 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("请输入你要反射的类名：");
            String path=sc.nextLine();
            Class cla=Class.forName(path);
            System.out.println("类名："+cla.getName());

            Field[] fields = cla.getDeclaredFields();
            for(Field f:fields){
                System.out.println("属性名："+f.getName());
                int m=f.getModifiers();
                String modifier=null;
                switch (m){
                    case 1:modifier="public";
                    break;
                    case 2:modifier="private";
                }
                System.out.println("属性访问标志符："+modifier);
            }
            Method[] methods = cla.getDeclaredMethods();
            for(Method m:methods){
                System.out.println("方法名："+m.getName());
                int mo=m.getModifiers();
                String modifier=null;
                switch (mo){
                    case 1:modifier="public";
                        break;
                    case 2:modifier="private";
                }
                System.out.println("方法访问标志符："+modifier);

            }
            Constructor[] cons = cla.getConstructors();
            if(cons!=null&&cons.length!=0){
                for (Constructor c:cons){
                    System.out.println("构造方法名："+c.getName());
                }
            }

            Object o=cla.newInstance();
            if(o instanceof Showable){
                Showable p=(Showable)o;
                p.show();
            }
            System.out.println("===========================================");
            System.out.println("===========================================");

            Map<String,String> map = new HashMap<String, String>();
            map.put("name","张三");
            map.put("age","30");
            System.out.println( setValue(map,cla).toString());

        }

    }

    public static Object setValue(Map<String,String> map,Class cls) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object o=cls.newInstance();
        Method[] methods = cls.getDeclaredMethods();
        if(methods!=null&&methods.length!=0){
            for(Method m:methods){
                if(m.getName().startsWith("set")){
                    String mName=m.getName();
                    String fName=mName.substring(3).toLowerCase();
                    String value=map.get(fName);
                    if("java.lang.Integer".equalsIgnoreCase(m.getParameterTypes()[0].getTypeName())||"Int".equalsIgnoreCase(m.getParameterTypes()[0].getTypeName())){
                        m.invoke(o,Integer.parseInt(value));
                    }else{
                        m.invoke(o,value);
                    }
                }
            }
        }
        return o;
    }
}
