package com.aop.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {

    @Before("com.aop.common.PointCutsV4.allOrderAndServiceV4()")
    public void doBefore(JoinPoint joinPoint ){
        log.info("Before - " + joinPoint.getSignature().getDeclaringTypeName() + " / " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "com.aop.common.PointCutsV4.allOrderAndServiceV4()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result){
        log.info("AfterReturning - " + joinPoint.getSignature().getDeclaringTypeName() + " / " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "com.aop.common.PointCutsV4.allOrderAndServiceV4()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e){
        log.info("AfterThrowing - " + joinPoint.getSignature().getDeclaringTypeName() + " / " + joinPoint.getSignature().getName());
    }

    @After("com.aop.common.PointCutsV4.allOrderAndServiceV4()")
    public void doAfter(JoinPoint joinPoint){
        log.info("After - " + joinPoint.getSignature().getDeclaringTypeName() + " / " + joinPoint.getSignature().getName());
    }
}
