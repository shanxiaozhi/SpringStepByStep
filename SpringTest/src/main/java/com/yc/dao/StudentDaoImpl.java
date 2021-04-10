package com.yc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author liyan
 * @create 2021-04-2021/4/4-15:22
 */
@Component
public class StudentDaoImpl implements StudentDao {
    @Override
    public int add( String name) {
        System.out.println("jap 添加学生" +name);
        Random random = new Random();
        return random.nextInt();
    }
    @Override
    public void update(String name) {
        System.out.println("jap 更新学生" +name);
    }
}
