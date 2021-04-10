package com.yc.biz;

import com.yc.Appconfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.annotation.Resources;

import static org.junit.Assert.*;

/**
 * @author liyan
 * @create 2021-04-2021/4/9-19:15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Appconfig.class)
public class StudentBizImplTest {
    @Autowired
    StudentBiz studentBiz;

    @Test
    public void add() {
        studentBiz.add("鸡屎哥");
    }
    @Test
    public void update() {
        studentBiz.update("唐鸡屎");
    }
}