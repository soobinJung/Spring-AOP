package com.aop;

import com.aop.common.AspectV4PointCut;
import com.aop.common.AspectV5Order;
import com.aop.order.OrderRepository;
import com.aop.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import({AspectV5Order.LogAspect.class, AspectV5Order.TransactionAspect.class})
@Slf4j
@SpringBootTest
public class AopV5Test {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    /**
     * AOP Proxy 가 적용 되었는지 확인
     */
    @Test
    public void aopInfo(){
        log.info("Proxy 가 적용 되었는지 체크 : " + AopUtils.isAopProxy(orderService));
        log.info("Proxy 가 적용 되었는지 체크 : " + AopUtils.isAopProxy(orderRepository));

        orderService.save("item1");
    }
}
