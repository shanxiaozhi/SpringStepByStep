package com.yc.biz;

import com.yc.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author liyan
 * @create 2021-04-2021/4/4-15:26
 */
@Component
public class StudenBiz {

    @Autowired
    @Qualifier("studenMysqlDaoimpl")
    public StudentDao studentDao;


}
