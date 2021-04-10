package com.yc.springFramework.context;

import com.yc.springFramework.stereotype.*;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * @author liyan
 * @create 2021-04-2021/4/5-11:42
 */
public class MyAnnationConfigApplicationContext implements MyApplicationContext {
    private Map<String, Object> beans = new HashMap<>();
    private Set<Class<?>> classes = new HashSet<>();

    public MyAnnationConfigApplicationContext(Class<?>... componnentClasses) throws IOException, InstantiationException, IllegalAccessException, InvocationTargetException {
        register(componnentClasses);
    }

    @Override
    public Object getBean(String beanId) {
        return beans.get(  beanId);
    }

    @Override
    public Object getBean(Class clz) {
        Collection<Object> collection=beans.values();
        Iterator<Object> its=collection.iterator();
        while( its.hasNext() ) {
            Object obj=its.next();
            if(  obj.getClass().getName().equals( clz.getName() )) {
                return obj;
            }
        }
        return null;
    }

    private void register(Class<?>[] componnentClasses) throws IOException, IllegalAccessException, InstantiationException, InvocationTargetException {
        if (componnentClasses == null || componnentClasses.length <= 0) {
            throw new RuntimeException("没有指定的配置类");
        }

        for (Class cl : componnentClasses) {
            if (!cl.isAnnotationPresent(MyConfiguration.class)) {
                continue;
            }
            String[] baskPackages = getAppConfigBasePackages(cl);
            if (cl.isAnnotationPresent(MyComponentScan.class)) {
                MyComponentScan mcs = (MyComponentScan) cl.getAnnotation(MyComponentScan.class);
                if (mcs.basePackages() != null && mcs.basePackages().length > 0) {
                    baskPackages = mcs.basePackages();
                }
            }

            Object object = cl.newInstance();
            handleAtMyBean(cl, object);
            for (int i = 0; i < baskPackages.length; i++) {
                scanPackageClassAndSubPackageInClasses(baskPackages[i]);
            }

        }


    }

    private void handleAtMyBean(Class cl, Object object) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = cl.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MyBean.class)) {
                Object o = method.invoke(object);
                //加入处理@MyBean注解对应的方法所实例化的类中的@MypostConstruct对应的方法
                handlePostConstruct(o);
                System.out.println(method.getName());
                beans.put(method.getName(), o);

            }
        }
    }

    private String[] getAppConfigBasePackages(Class cl) {
        String[] path = new String[1];
        path[0] = cl.getPackage().getName();
        return path;
    }


    private void scanPackageClassAndSubPackageInClasses(String packageName) throws IOException {
        String packagename = packageName.replaceAll("\\.", "/");
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packagename);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            findPackageClass(url.getFile(), packagename);
            createComponentBean(classes);
        }
    }

    private void createComponentBean(Set<Class<?>> classes) {
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(MyComponent.class)) {
                String beanName = clazz.getAnnotation(MyComponent.class).value();
                addComponentBean(clazz,beanName);
            }if (clazz.isAnnotationPresent(MyService.class)) {
                String beanName = clazz.getAnnotation(MyService.class).value();
                addComponentBean(clazz,beanName);
            }if (clazz.isAnnotationPresent(MyController.class)) {
                String beanName = clazz.getAnnotation(MyController.class).value();
                addComponentBean(clazz,beanName);
            }if (clazz.isAnnotationPresent(MyRepository.class)) {
                String beanName = clazz.getAnnotation(MyRepository.class).value();
                addComponentBean(clazz,beanName);
            }
        }
//        for(String s:beans.keySet()){
//            System.out.println(s);
//        }
        handleDi(classes);

    }

    private void addComponentBean(Class clazz,String beanName){

        if (beanName == null || "".equals(beanName)) {
            String className = clazz.getSimpleName();
            beanName = className.substring(0, 1).toLowerCase() + className.substring(1);
        }
        try {
            if (beans.get(beanName) == null) {
                Object o = clazz.newInstance();
                handlePostConstruct(o);
                beans.put(beanName, o);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void handleDi(Set<Class<?>> classes) {

        for(Class<?> clazz:classes){
            Field[] fs=clazz.getDeclaredFields();
            //获取类注解@MyComponent的value值
            String beanName=null;

            if (clazz.isAnnotationPresent(MyComponent.class)) {
                beanName = clazz.getAnnotation(MyComponent.class).value();
            }else if (clazz.isAnnotationPresent(MyService.class)) {
                 beanName = clazz.getAnnotation(MyService.class).value();
            }else if (clazz.isAnnotationPresent(MyController.class)) {
                 beanName = clazz.getAnnotation(MyController.class).value();
            }else if (clazz.isAnnotationPresent(MyRepository.class)) {
                 beanName = clazz.getAnnotation(MyRepository.class).value();
            }

            if(beanName==null||"".equals(beanName)){
                //如果value值为空，则直接取类名
                beanName=clazz.getSimpleName().substring(0,1).toLowerCase()+clazz.getSimpleName().substring(1);
            }
            //遍历字段
            for(Field f:fs){
                if(f.isAnnotationPresent(MyAutowired.class)){
                   createAutoWried(f,beanName);
                }else if(f.isAnnotationPresent(MyResource.class)){
                    createResource(f,beanName);
                }
            }
        }

        for(Class<?> clazz:classes){
            Method[] ms=clazz.getDeclaredMethods();
            //获取类注解@MyComponent的value值
            String beanName=null;
            if (clazz.isAnnotationPresent(MyComponent.class)) {
                beanName = clazz.getAnnotation(MyComponent.class).value();
            }else if (clazz.isAnnotationPresent(MyService.class)) {
                beanName = clazz.getAnnotation(MyService.class).value();
            }else if (clazz.isAnnotationPresent(MyController.class)) {
                beanName = clazz.getAnnotation(MyController.class).value();
            }else if (clazz.isAnnotationPresent(MyRepository.class)) {
                beanName = clazz.getAnnotation(MyRepository.class).value();
            }
            if(beanName==null||"".equals(beanName)){
                //如果value值为空，则直接取类名
                beanName=clazz.getSimpleName().substring(0,1).toLowerCase()+clazz.getSimpleName().substring(1);
            }
            //遍历字段
            for(Method m:ms){
                if(m.isAnnotationPresent(MyAutowired.class)){
                    createAutoWried(m,beanName);
                }else if(m.isAnnotationPresent(MyResource.class)){
                    if(m.getName().startsWith("set")){
                        createResource(m,beanName);
                    }

                }
            }
        }

    }

    private void createResource(Field f, String beanName) {
        MyResource resource=f.getAnnotation(MyResource.class);
        String beanId=resource.name();
        Class type=resource.type();
        try {
            if(beanId!=null&&!beanId.equalsIgnoreCase("")&&(!type.getTypeName().equalsIgnoreCase("java.lang.Object"))){
                String pname=f.getType().getSimpleName();
                beanId=pname.substring(1).toLowerCase()+pname.substring(1);
                Object o=beans.get(beanId);
                if(o!=null){
                    if(o.getClass().getTypeName().equalsIgnoreCase(type.getTypeName())){
                        f.setAccessible(true);
                        f.set(beans.get(beanName),o);
                    }else{
                        throw new RuntimeException("没有找到指定类型");
                    }
                }
            }else if(beanId!=null&&!beanId.equalsIgnoreCase("")&&(type.getTypeName().equalsIgnoreCase("java.lang.Object"))){
                Object o=beans.get(beanId);
                if(o!=null){
                    f.setAccessible(true);
                    f.set(beans.get(beanName),o);
                }else{
                    throw new RuntimeException("没有找到指定类型");
                }
            }else if((beanId==null||beanId.equalsIgnoreCase(""))&&(type.getTypeName().equalsIgnoreCase("java.lang.Object"))){
                String pname=f.getType().getSimpleName();
                beanId=pname.substring(0,1).toLowerCase()+pname.substring(1);
                Object o=beans.get(beanId);
                if(o!=null){
                        f.setAccessible(true);
                        f.set(beans.get(beanName),o);
                }else{
                    throw new RuntimeException("没有找到指定类型");
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }

    private void createAutoWried(Field f,String beanName){
        MyAutowired MyAutowired=f.getAnnotation(MyAutowired.class);
        boolean isnull=MyAutowired.required();
//                    获取字段的类型
        Class type=f.getType();
        Object bean=null;
        //用来存储beans里所有是该类型的对象
        Map<String,Object> aumap=new HashMap<>();
        for(String key:beans.keySet()){
            bean = beans.get(key);
            if(type.isAssignableFrom(bean.getClass())){
                aumap.put(key,bean);
            }
        }
        try{
            if(aumap.size()==0){
                if(isnull==true){
                    throw new RuntimeException("没有找到"+bean.getClass().getSimpleName());
                }
            }
            if(f.isAnnotationPresent(MyQualifler.class)){
                MyQualifler myQualifler=f.getAnnotation(MyQualifler.class);
                String myQualiflerName=myQualifler.value();
                if(myQualiflerName!=null&&myQualiflerName!=""){
                    if(aumap.get(myQualiflerName)!=null){
                        Object o=beans.get(beanName);
                        f.setAccessible(true);
                        f.set(o,aumap.get(myQualiflerName));
                    }else{
                        if (isnull==true){
                            throw new RuntimeException("没有匹配到该名字的bean"+bean.getClass().getSimpleName()+"的类");
                        }
                    }
                }
            }else if(aumap.size()>=2){
                if(isnull==true){
                    throw new RuntimeException("发现多个类型的"+bean.getClass().getSimpleName()+"的类");
                }
            }else if(aumap.size()==1){
                Object o=beans.get(beanName);
                Object b=aumap.values().iterator().next();
                f.setAccessible(true);
                f.set(o,b);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private void createResource(Method m, String beanName) {
        MyResource resource=m.getAnnotation(MyResource.class);
        String beanId=resource.name();
        Class type=resource.type();
        try {
            if(beanId!=null&&!beanId.equalsIgnoreCase("")&&(!type.getTypeName().equalsIgnoreCase("java.lang.Object"))){
                Object o=beans.get(beanId);
                if(o!=null){
                    if(o.getClass().getTypeName().equalsIgnoreCase(type.getTypeName())){
                        m.invoke(beans.get(beanName),o);
                    }else{
                        throw new RuntimeException("没有找到指定类型");
                    }
                }
            }else if(beanId!=null&&!beanId.equalsIgnoreCase("")&&(type.getTypeName().equalsIgnoreCase("java.lang.Object"))){
                Object o=beans.get(beanId);
                if(o!=null){
                    m.invoke(beans.get(beanName),o);
                }else{
                    throw new RuntimeException("没有找到指定类型");
                }
            }else if((beanId==null||beanId.equalsIgnoreCase(""))&&(type.getTypeName().equalsIgnoreCase("java.lang.Object"))){
                String pname=m.getParameterTypes()[0].getSimpleName();
                beanId=pname.substring(0,1).toLowerCase()+pname.substring(1);
                Object o=beans.get(beanId);
                if(o!=null){
                    m.invoke(beans.get(beanName),o);
                }else{
                    throw new RuntimeException("没有找到指定类型");
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    private void createAutoWried(Method m,String beanName){
        MyAutowired MyAutowired=m.getAnnotation(MyAutowired.class);
        boolean isnull=MyAutowired.required();
        Class type=m.getParameterTypes()[0];
        Object bean=null;
        Map<String,Object> aumap=new HashMap<>();
        for(String key:beans.keySet()){
            bean = beans.get(key);
            if(type.isAssignableFrom(bean.getClass())){
                aumap.put(key,bean);
            }
        }
        try{
            if(aumap.size()==0){
                if(isnull==true){
                    throw new RuntimeException("没有找到"+bean.getClass().getSimpleName());
                }
            }
            if(m.isAnnotationPresent(MyQualifler.class)){
                MyQualifler myQualifler=m.getAnnotation(MyQualifler.class);
                String myQualiflerName=myQualifler.value();
                if(myQualiflerName!=null&&myQualiflerName!=""){
                    if(aumap.get(myQualiflerName)!=null){
                        Object o=beans.get(beanName);
                        m.invoke(o,aumap.get(myQualiflerName));
                    }else{
                        if (isnull==true){
                            throw new RuntimeException("没有匹配到该名字的bean"+bean.getClass().getSimpleName()+"的类");
                        }
                    }
                }
            }else if(aumap.size()>=2){
                if(isnull==true){
                    throw new RuntimeException("发现多个类型的"+bean.getClass().getSimpleName()+"的类");
                }
            }else if(aumap.size()==1){
                Object o=beans.get(beanName);
                Object b=aumap.values().iterator().next();
                m.invoke(o,b);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }





    private void handlePostConstruct(Object o) {
        Method[] methods = o.getClass().getDeclaredMethods();
        for (Method m : methods) {
            Annotation[] methodans = m.getAnnotations();
            for (Annotation an : methodans) {
                if (an instanceof MyPostConstruct) {
                    try {
                        m.invoke(o, null);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public void findPackageClass(String packagpath, String packagename) {
        File[] files = new File(packagpath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().endsWith(".class") || file.isDirectory();
            }
        });
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    findPackageClass(file.getAbsolutePath(), packagename + "." + file.getName());
                } else {
                    URLClassLoader uc = new URLClassLoader(new URL[]{});
                    try {
                        classes.add(uc.loadClass(packagename.replace("/", ".") + "." + file.getName().replace(".class", "")));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            uc.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
