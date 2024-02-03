package com.aop.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

/**
 * @Aspect 의 순서를 제어하는 @Order 는 Class 단위에 적용 가능하기 때문에 별도의 클래스로 분리하여 작성
 */
@Slf4j
public class AspectV5Order {

    @Aspect
    @Order(1)
    public static class LogAspect{
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
    }

    @Aspect
    @Order(2)
    public static class TransactionAspect{

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
}
