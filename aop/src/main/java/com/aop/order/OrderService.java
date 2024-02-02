package com.aop.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public String save(String itemId){
        log.info("OrderService save started");
        return orderRepository.save(itemId);
    }
}
