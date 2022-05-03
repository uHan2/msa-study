package com.example.orderservice.entity;

import com.example.orderservice.model.request.CreateOrderRequest;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@ToString
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

    private String productId;

    private String userId;

    @CreatedDate
    private LocalDateTime createDate;

    public OrderEntity(CreateOrderRequest createOrderRequest, String userId) {
        this.quantity = createOrderRequest.getQuantity();
        this.unitPrice = createOrderRequest.getUnitPrice();
        this.totalPrice = this.quantity * this.unitPrice;
        this.productId = createOrderRequest.getProductId();
        this.userId = userId;
    }
}