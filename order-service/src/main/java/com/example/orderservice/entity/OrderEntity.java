package com.example.orderservice.entity;

import com.example.orderservice.model.request.OrderRequest;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "tbl_order")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer quantity;

    private Integer unitPrice;

    private Integer totalPrice;

    private String productCd;

    private String userId;

    public OrderEntity(OrderRequest orderRequest) {
        this.quantity = orderRequest.getQuantity();
        this.unitPrice = orderRequest.getUnitPrice();
        this.totalPrice = this.quantity * this.unitPrice;
        this.productCd = orderRequest.getProductCd();
        this.userId = orderRequest.getUserId();
    }
}