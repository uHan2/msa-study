package com.example.productservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {

    private String productCd;

    private String productName;

    private Integer quantity;

    private Integer unitPrice;

    private Integer totalPrice;

    private String orderId;

    private String userId;

}
