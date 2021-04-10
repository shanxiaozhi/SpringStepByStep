package com.yc;

import com.yc.biz.StudenBiz;
import com.yc.dao.StudenMysqlDaoimpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
 * @author liyan
 * @create 2021-04-2021/4/4-14:45
 */
@Configuration
@ComponentScan("com.yc")
public class SpringTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(SpringTest.class);
        StudenBiz biz =applicationContext.getBean(StudenBiz.class);
        biz.studentDao.add("hello");
        biz.studentDao.update("world");
    }

    @Bean
    public StudenMysqlDaoimpl studenMysqlDaoimpl(){
        return new StudenMysqlDaoimpl();
    }
}
