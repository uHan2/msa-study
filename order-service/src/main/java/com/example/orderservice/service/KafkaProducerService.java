package com.example.orderservice.service;

import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.message.CancelOrderEvent;
import com.example.orderservice.message.CreateOrderEvent;
import com.example.orderservice.model.request.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

    private final StreamBridge streamBridge;

    public void sendCreateOrder(CreateOrderRequest createOrderRequest, Long orderId) {
        CreateOrderEvent event = new CreateOrderEvent(createOrderRequest, orderId);
        streamBridge.send("createOrder-out-0", event);
        log.info("createOrderEvent 발행 : " + event);
    }

    public void sendCancelOrder(OrderEntity orderCancelEvent) {
        CancelOrderEvent event = new CancelOrderEvent(orderCancelEvent);
        streamBridge.send("cancelOrderByUser-out-0", event);
        log.info("cancelOrderEvent 발행 : " + event);
    }
}
