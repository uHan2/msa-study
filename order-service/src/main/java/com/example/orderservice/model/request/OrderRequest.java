package com.example.orderservice.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderRequest {

    private Integer quantity;

    private Integer unitPrice;

    private String productId;

    private String userId;

    private String message = "주문 발생";

}

