package com.yc;

import com.yc.biz.StudentBiz;
import com.yc.biz.StudentBizImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author liyan
 * @create 2021-04-2021/4/9-19:25
 */
public class Test {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext
        ApplicationContext context=new AnnotationConfigApplicationContext(Appconfig.class);
        StudentBiz bean = context.getBean(StudentBiz.class);
        bean.add("liyan");

    }
}
