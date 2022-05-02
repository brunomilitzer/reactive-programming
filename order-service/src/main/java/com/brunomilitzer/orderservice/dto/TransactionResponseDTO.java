package com.brunomilitzer.orderservice.dto;

import com.brunomilitzer.orderservice.enums.TransactionStatus;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class TransactionResponseDTO {

    private Long userId;
    private Integer amount;
    private TransactionStatus status;
}
