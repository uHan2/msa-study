package com.example.orderservice.service;

import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.model.request.CreateOrderRequest;
import com.example.orderservice.model.response.CancelOrderResponse;
import com.example.orderservice.model.response.CreateOrderResponse;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest, String userId) {
        OrderEntity savedOrder = orderRepository.save(new OrderEntity(createOrderRequest, userId));
        return new CreateOrderResponse(savedOrder);
    }

    @Transactional(readOnly = true)
    public List<OrderEntity> getOrders(String userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public CancelOrderResponse cancelOrder(String userId, Long orderId) {
        return null;
    }
}
