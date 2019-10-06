package com.yuki.test.aop.aspectj;

import com.yuki.test.aop.plus.UserValidator;
import com.yuki.test.aop.plus.impl.UserValidatorImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
@Aspect
//确定执行顺序
//@Order(1)
public class EatAspect implements Ordered {
    //确定执行顺序
    @Override
    public int getOrder() {
        return 0;
    }

    //引入
    @DeclareParents(value = "com.yuki.test.aop.service.impl.UserServiceImpl+",defaultImpl = UserValidatorImpl.class)
    public UserValidator userValidator;

    //定义切点
    @Pointcut("execution(* com.yuki.test.aop.service.impl.UserServiceImpl.eat(..)) && args(count)")
    public void pointcut1(Integer count){

    }

    //定义切点
    @Pointcut("execution(* com.yuki.test.aop.service.impl.UserServiceImpl.eat(..))")
    public void pointcut(){

    }

    //前置通知
    @Before("pointcut()")
    public void before(){
        System.out.println("吃饭之前");
    }

    //后置通知
    @After("pointcut()")
    public void after(){
        System.out.println("吃饭之后");
    }

    //返回通知
    @AfterReturning(pointcut = "pointcut()",returning = "rvt")
    public void returning(Object rvt){
        //确定返回值 确定相应操作
        boolean b = rvt  == null;
        System.out.println("rvt == null : " + b);
        System.out.println("返回结果");
    }

    //异常通知
    @AfterThrowing(value = "pointcut()",throwing = "exp")
    public void afterThrowing(Exception exp){
        //转换异常类型 执行相应处理
        System.out.println("抛出了异常");
    }

//    环绕通知
    @Around("pointcut()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("开始环绕");
        //执行目标方法
        proceedingJoinPoint.proceed();
        System.out.println("结束环绕");
        //抛出出异常
        throw new Exception("around异常");
    }


}
