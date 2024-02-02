package com.aop.common;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class AspectV1 {

    @Around("execution(* com.aop..*.*(..))")
    public Object doLogging(ProceedingJoinPoint joinPoint) throws Throwable {

        /** 시작 시간 **/
        log.info("Start - " + joinPoint.getSignature().getDeclaringTypeName() + " / " + joinPoint.getSignature().getName());

        /** 실제 타겟이 호출되는 시점 **/
        Object result = joinPoint.proceed();

        /** 종료 시간 **/
        log.info("End - " + joinPoint.getSignature().getDeclaringTypeName() + " / " + joinPoint.getSignature().getName());
        return result;
    }
}
