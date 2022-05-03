package com.example.productservice.message;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CancelOrderEvent {

    private EventType eventType;

    private Long orderId;

    private String productId;

    private Integer quantity;

    private String message;

    private String timestamp;


    public CancelOrderEvent(CreateOrderEvent event) {
        this.eventType = EventType.OUT_OF_STOCK;
        this.orderId = event.getOrderId();
        this.productId = event.getProductId();
        this.quantity = event.getQuantity();
        this.message = "재고 부족으로 인한 주문 취소";
        SimpleDateFormat defaultSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        this.timestamp = defaultSimpleDateFormat.format(new Date());
    }
}
