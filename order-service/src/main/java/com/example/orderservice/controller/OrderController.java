package com.example.orderservice.controller;

import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.model.request.OrderRequest;
import com.example.orderservice.service.KafkaProducerService;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final KafkaProducerService kafkaProducerService;

    @GetMapping("/health-check")
    public String info(@Value("${server.port}") String port) {
        log.info("@@@Order Service Health Check@@@");
        return "order-service 서비스의 기본 동작 Port: {" + port + "}";
    }

    @PostMapping("/{userId}/order")
    public ResponseEntity<OrderEntity> createOrder(@PathVariable String userId, @RequestBody OrderRequest orderRequest) {
        orderRequest.setUserId(userId);
        kafkaProducerService.send(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderRequest));
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<OrderEntity>> getOrders(@PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrders(userId));
    }
}
