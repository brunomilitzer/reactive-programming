package com.brunomilitzer.orderservice.entity;

import com.brunomilitzer.orderservice.enums.OrderStatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@ToString
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {

    @Id
    @GeneratedValue
    private Long id;

    private String productId;

    private Long userId;

    private Integer amount;

    private OrderStatus status;
}
