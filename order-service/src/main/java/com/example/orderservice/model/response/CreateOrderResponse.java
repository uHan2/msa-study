package com.example.orderservice.model.response;

import com.example.orderservice.entity.OrderEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderResponse {

    private String productId;

    private String userId;

    private Long orderId;

    public CreateOrderResponse(OrderEntity savedOrder) {
        this.productId = savedOrder.getProductId();
        this.userId = savedOrder.getUserId();
        this.orderId = savedOrder.getId();
    }
}
