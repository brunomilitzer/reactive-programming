package com.brunomilitzer.orderservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class PurchaseOrderRequestDTO {

    private Long userId;
    private String productId;
}
