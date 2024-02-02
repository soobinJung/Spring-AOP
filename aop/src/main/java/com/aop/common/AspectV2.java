package com.aop.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Pointcut 을 사용한 Aspect
 *
 * - 메서드 이름과 파라메터를 합쳐서 포인트컷 시그니쳐라 한다.
 * - 포인트컷을 별도의 메서드로 분리하고, @Pointcut 을 사용하여 재사용한다.
 * - 메서드의 반환 타입은 void 이고 코드 내용은 비어있는 메서드로 선언한다.
 */
@Slf4j
@Aspect
public class AspectV2 {

    /**
     * PointCut 시그니쳐
     */
    @Pointcut("execution(* com.aop..*.*(..))")
    public void allOrderV2(){}

    @Around("allOrderV2()")
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
