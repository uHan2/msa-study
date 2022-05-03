package com.example.orderservice.service;

import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.message.CancelOrderEvent;
import com.example.orderservice.model.request.CreateOrderRequest;
import com.example.orderservice.model.response.CreateOrderResponse;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaProducerService kafkaProducerService;

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest, String userId) {
        OrderEntity savedOrder = orderRepository.save(new OrderEntity(createOrderRequest, userId));
        kafkaProducerService.sendCreateOrder(createOrderRequest, savedOrder.getId());
        return new CreateOrderResponse(savedOrder);
    }

    @Transactional(readOnly = true)
    public List<OrderEntity> getOrders(String userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Transactional
    public Long cancelOrder(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() -> new ServiceException("주문 정보가 올바르지 않습니다."));
        kafkaProducerService.sendCancelOrder(order);
        orderRepository.delete(order);
        return orderId;
    }

    @Transactional
    public void deleteOrder(CancelOrderEvent cancelOrderEvent) {
        log.info("CancelOrderEvent 수신 : " + cancelOrderEvent.toString());
        orderRepository.deleteById(cancelOrderEvent.getOrderId());
    }
}
