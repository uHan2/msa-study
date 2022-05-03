package com.example.orderservice.message;

import com.example.orderservice.model.request.CreateOrderRequest;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CreateOrderEvent {

    private String productId;

    private Integer quantity;

    private String message;

    private Long orderId;

    private String timestamp;

    public CreateOrderEvent(CreateOrderRequest createOrderRequest, Long orderId) {
        this.productId = createOrderRequest.getProductId();
        this.quantity = createOrderRequest.getQuantity();
        this.orderId = orderId;
        this.message = "주문 발생";
        SimpleDateFormat defaultSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        this.timestamp = defaultSimpleDateFormat.format(new Date());
    }
}
