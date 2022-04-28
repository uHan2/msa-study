package com.example.orderservice.service;

import com.example.orderservice.message.CancelOrderEvent;
import com.example.orderservice.message.CreateOrderEvent;
import com.example.orderservice.model.request.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

    private final StreamBridge streamBridge;

    public void sendCreateOrder(CreateOrderRequest createOrderRequest) {
        CreateOrderEvent event = new CreateOrderEvent(createOrderRequest);
        streamBridge.send("createOrder-out-0", event);
        log.info(createOrderRequest.toString());
    }

    public void sendCancelOrder(String userId, Long orderId) {
        CancelOrderEvent event = new CancelOrderEvent(userId, orderId);
    }
}
