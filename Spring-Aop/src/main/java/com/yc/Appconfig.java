package com.yc;


import com.yc.biz.StudentBizImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration //表示当前类是一个配置类
@ComponentScan(basePackages = "com.yc") //将来要托管的bean要扫描的包及子包
@EnableAspectJAutoProxy
public class Appconfig {
    @Bean
    public StudentBizImpl studentBizImpl(){
        return  new StudentBizImpl();
    }

}
