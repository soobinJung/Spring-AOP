package com.aop.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Pointcut 을 조합하여 사용한 Aspect
 */
@Slf4j
@Aspect
public class AspectV3 {

    @Pointcut("execution(* com.aop.order..*(..))")
    public void allOrderV3(){}

    /**
     * 파일명이 **Service 인 경우만 적용
     */
    @Pointcut("execution(* *..*Service.*(..))")
    public void allServiceV3(){}

    @Around("allOrderV3()")
    public Object doLogging(ProceedingJoinPoint joinPoint) throws Throwable {

        /** 시작 시간 **/
        log.info("Start - " + joinPoint.getSignature().getDeclaringTypeName() + " / " + joinPoint.getSignature().getName());

        /** 실제 타겟이 호출되는 시점 **/
        Object result = joinPoint.proceed();

        /** 종료 시간 **/
        log.info("End - " + joinPoint.getSignature().getDeclaringTypeName() + " / " + joinPoint.getSignature().getName());
        return result;
    }

    /**
     * @Around 를 조합하여 사용한 트랜잭션 처리
     */
    @Around("allOrderV3() && allServiceV3()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

        try{
            /** 시작 시간 **/
            log.info("Start - " + joinPoint.getSignature().getDeclaringTypeName() + " / " + joinPoint.getSignature().getName());

            /** 실제 타겟이 호출되는 시점 **/
            Object result = joinPoint.proceed();

            /** 종료 시간 **/
            log.info("End - " + joinPoint.getSignature().getDeclaringTypeName() + " / " + joinPoint.getSignature().getName());
            return result;
        }
        catch (Exception e){
            log.info("Transaction Rollback - " + joinPoint.getSignature().getDeclaringTypeName() + " / " + joinPoint.getSignature().getName());
            throw e;
        }
        finally {
            log.info("Error - " + joinPoint.getSignature().getDeclaringTypeName() + " / " + joinPoint.getSignature().getName());
        }
    }
}
