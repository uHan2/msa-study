package com.example.orderservice.message;

import com.example.orderservice.model.request.CreateOrderRequest;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CreateOrderEvent {

    private String productId;

    private Integer quantity;

    private String message;

    public CreateOrderEvent(CreateOrderRequest createOrderRequest) {
        this.productId = createOrderRequest.getProductId();
        this.quantity = createOrderRequest.getQuantity();
        this.message = "주문 발생";
    }
}
