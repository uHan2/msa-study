package com.example.productservice.message;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateEvent {

    private String productId;

    private Integer quantity;

}
