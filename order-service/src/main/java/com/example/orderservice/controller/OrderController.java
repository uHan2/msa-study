package com.example.orderservice.controller;

import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.model.request.CreateOrderRequest;
import com.example.orderservice.model.response.CancelOrderResponse;
import com.example.orderservice.model.response.CreateOrderResponse;
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

    /**
     * 주문 생성
     *
     * @param userId             회원 ID
     * @param createOrderRequest 수량, 단가, 물품 ID
     * @return 물품 ID, 회원 ID, 주문 ID
     */
    @PostMapping("/{userId}/order")
    public ResponseEntity<CreateOrderResponse> createOrder(@PathVariable String userId, @RequestBody CreateOrderRequest createOrderRequest) {
        kafkaProducerService.sendCreateOrder(createOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(createOrderRequest, userId));
    }

    @PostMapping("/{userId}/order-cancel/{orderId}")
    public ResponseEntity<CancelOrderResponse> cancelOrder(@PathVariable String userId, @PathVariable Long orderId) {
        kafkaProducerService.sendCancelOrder(userId, orderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.cancelOrder(userId, orderId));
    }

    /**
     * 주문 조회
     *
     * @param userId 회원 ID
     * @return 주문 LISt
     */
    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<OrderEntity>> getOrders(@PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrders(userId));
    }
}
