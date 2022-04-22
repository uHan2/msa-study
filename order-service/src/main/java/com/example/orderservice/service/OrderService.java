package com.example.orderservice.service;

import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.model.request.OrderRequest;
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
    public OrderEntity createOrder(OrderRequest orderRequest) {
        OrderEntity order = new OrderEntity(orderRequest);
        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public List<OrderEntity> getOrders(String userId) {
        return orderRepository.findAllByUserId(userId);
    }
}
