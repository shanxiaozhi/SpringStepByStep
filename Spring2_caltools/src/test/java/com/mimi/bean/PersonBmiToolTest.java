package com.mimi.bean;


import com.AppConfig;
import com.huwei.bean.Container;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

/**
 * @author liyan
 * @create 2021-04-2021/4/5-9:34
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class PersonBmiToolTest {
    @Autowired
    private ApplicationContext ac;
    @Autowired
    private PersonBmiTool pbt;
    @Autowired
    private Container c;
    @Autowired
    private Random r;


    @Test
    public void measure() {
        Person p1=new Person("张三",1.70,80);
        Person p2=new Person("李四",1.70,80);
        Person p3=new Person("王五",1.70,80);

        c.save(p1);
        c.save(p2);
        c.save(p3);

        for(   int i=0;i<1000;i++){
            //  Math.random()   生成 0-1之间的小数
            Person p7=new Person( "王八"+i,   1+Math.random()    ,   r.nextInt(80)+30  );
            c.save(p7);
        }

        Person max=(Person)c.getMax();   //取最大值
        Person min=(Person)c.getMin();   //最最小值

        System.out.println("最大值:"+  max.getName() );
        System.out.println("最小值:"+ min.getName());

        System.out.println("平均bmi:"+ c.getAvg() );

        Object[] objs=c.getObjs();
        for(   Object o: objs ){
            Person pp=(Person)o;
            System.out.println(    pp.getName()+"\t"+pp.getHeight()+"\t"+pp.getWeight() +"\t"+   pbt.measure(   pp  )  );
        }


    }
}