package com.example.orderservice.service;

import com.example.orderservice.message.CancelOrderEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class KafkaConsumerService {

//    @Bean
//    public Consumer<CancelOrderEvent> cancelOrderByUser(OrderService orderService) {
//        return orderService::deleteOrder;
//    }

    @Bean
    public Consumer<CancelOrderEvent> cancelOrderBySystem(OrderService orderService) {
        return orderService::deleteOrder;
    }
}
