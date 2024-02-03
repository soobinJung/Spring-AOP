package com.aop.common;

import org.aspectj.lang.annotation.Pointcut;

/**
 * PointCuts 분리
 */
public class PointCutsV4 {

    @Pointcut("execution(* com.aop.order..*(..))")
    public void allOrderV4(){}

    @Pointcut("execution(* *..*Service.*(..))")
    public void allServiceV4(){}

    @Pointcut("allOrderV4() && allServiceV4()")
    public void allOrderAndServiceV4(){}
}
