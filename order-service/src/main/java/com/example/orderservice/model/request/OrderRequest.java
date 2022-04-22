package com.example.orderservice.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    private Integer quantity;

    private Integer unitPrice;

    private Integer totalPrice;

    private String productId;

    private String userId;

    private String message = "주문 발생";

}

