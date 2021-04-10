package com.yc.acpect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author liyan
 * @create 2021-04-2021/4/10-18:51
 */
@Aspect
@Component
@Order(value = 1)
public class LogAspect2 {



    @Pointcut("execution(* com.yc.biz.StudentBizImpl.add*(..))")
    private void add(){
    }


    @Before("com.yc.acpect.LogAspect2.add()")
    public void Log(JoinPoint jop){
        System.out.println("+++++"+"value = 1 前置增强"+"+++++");
    }



    @Around("com.yc.acpect.LogAspect2.add()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long start=System.currentTimeMillis();
        System.err.println("+++++"+"value = 1"+"+++++运行开始时间："+start);
        Object proceed = pjp.proceed();
        long end=System.currentTimeMillis();
        System.err.println("+++++"+"value = 1"+"+++++运行结束时间："+end);
        return proceed;
    }
}
