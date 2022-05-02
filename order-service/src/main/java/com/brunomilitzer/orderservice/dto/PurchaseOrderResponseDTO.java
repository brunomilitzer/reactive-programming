package com.brunomilitzer.orderservice.dto;

import com.brunomilitzer.orderservice.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class PurchaseOrderResponseDTO {

    private Long orderId;
    private Long userId;
    private String productId;
    private Integer amount;
    private OrderStatus status;
}
