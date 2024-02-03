package com.aop.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Around 분리
 */
@Slf4j
@Aspect
public class AspectV4PointCut {
    
    @Around("com.aop.common.PointCuts.allOrderV4()")
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
    @Around("com.aop.common.PointCuts.allOrderAndServiceV4()")
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
            log.info("Finally - " + joinPoint.getSignature().getDeclaringTypeName() + " / " + joinPoint.getSignature().getName());
        }
    }
}
