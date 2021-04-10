package com.yc.acpect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liyan
 * @create 2021-04-2021/4/9-19:35
 */
@Component
@Aspect
public class LogAspect {
    @Pointcut("execution(* com.yc.biz.StudentBizImpl.add*(..))")
    private void add(){
    }
    @Pointcut("execution(* com.yc.biz.StudentBizImpl.update*(..))")
    private void update(){
    }

//   @Pointcut("add()||update()")
//    private void addAndUpdate(){
//
//    }
//    @Before("com.yc.acpect.LogAspect.addAndUpdate()")
//    public void Log(JoinPoint jop){
////        Date d=new Date();
////        SimpleDateFormat sdf=new SimpleDateFormat();
////        String time = sdf.format(d);
//        System.err.println("============");
//        System.out.println(jop.getTarget());
//        System.out.println(jop.getKind());
//        System.out.println(jop.getSignature());
//        System.out.println(jop.getSourceLocation());
//        System.out.println(jop.getStaticPart());
//        System.out.println(jop.getThis());
//
////        System.out.println(time);
//    }
    @Around("com.yc.acpect.LogAspect.add()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long start=System.currentTimeMillis();
        System.err.println("运行开始时间："+start);
         Object proceed = pjp.proceed();
         long end=System.currentTimeMillis();
        System.err.println("运行结束时间："+end);
        return proceed;
    }

}
