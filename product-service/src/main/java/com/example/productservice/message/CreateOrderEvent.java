package com.example.productservice.message;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderEvent {

    private String productId;

    private Integer quantity;

    private String message;

    private Long orderId;

    private String timestamp;

}
