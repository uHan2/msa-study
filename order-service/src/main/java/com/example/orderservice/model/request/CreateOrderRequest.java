package com.example.orderservice.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateOrderRequest {

    private Integer quantity;

    private Integer unitPrice;

    private String productId;

}

