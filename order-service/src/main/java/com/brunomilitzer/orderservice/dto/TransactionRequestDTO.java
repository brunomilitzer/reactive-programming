package com.brunomilitzer.orderservice.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TransactionRequestDTO {

    private Long userId;
    private Integer amount;
}
