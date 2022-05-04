package com.example.orderservice.message;

import com.example.orderservice.entity.OrderEntity;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CancelOrderEvent {

    private Long orderId;

    private String productId;

    private Integer quantity;

    private String message;

    private String timestamp;

    public CancelOrderEvent(OrderEntity order) {
        this.orderId = order.getId();
        this.productId = order.getProductId();
        this.quantity = order.getQuantity();
        this.message = "주문 취소 발생";
        SimpleDateFormat defaultSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        this.timestamp = defaultSimpleDateFormat.format(new Date());
    }
}
