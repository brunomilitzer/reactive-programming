package com.brunomilitzer.orderservice.dto;

import com.brunomilitzer.orderservice.enums.TransactionStatus;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TransactionResponseDTO {

    private Long userId;
    private Integer amount;
    private TransactionStatus status;
}
