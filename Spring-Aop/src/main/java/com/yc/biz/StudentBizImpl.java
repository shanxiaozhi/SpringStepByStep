package com.yc.biz;

import com.yc.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class StudentBizImpl implements StudentBiz {

    @Autowired
    @Qualifier("studentDaoJpaImpl")
    private StudentDao studentDao;

    public StudentBizImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
    public StudentBizImpl() {
    }


    public  void setStudentDao(StudentDao studentDao){
        this.studentDao = studentDao;
    }

    @Override
    public int add(String name){
        System.out.println("=========业务层=======");
        System.out.println("用户名是否重复");
        int result = studentDao.add(name);
        System.out.println("=========业务操作结束=======");
        return  result;
    }

    @Override
    public void update(String name){
        System.out.println("=========业务层=======");
        System.out.println("用户名是否重复");
        studentDao.update(name);
        System.out.println("=========业务操作结束=======");
    }


}
