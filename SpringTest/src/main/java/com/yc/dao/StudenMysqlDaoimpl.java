package com.yc.dao;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author liyan
 * @create 2021-04-2021/4/4-17:14
 */
//@Component
public class StudenMysqlDaoimpl implements StudentDao {
    @Override
    public int add( String name) {
        System.out.println("mysql 添加学生" +name);
        Random random = new Random();
        return random.nextInt();
    }
    @Override
    public void update(String name) {
        System.out.println("mysql 更新学生" +name);
    }
}
