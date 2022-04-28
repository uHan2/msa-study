package com.example.orderservice.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelOrderResponse {

    private String userId;

    private Long orderId;

}
