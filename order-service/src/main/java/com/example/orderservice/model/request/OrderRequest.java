package com.example.orderservice.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    private Integer quantity;

    private Integer unitPrice;

    private String productCd;

    private String userId;

}
