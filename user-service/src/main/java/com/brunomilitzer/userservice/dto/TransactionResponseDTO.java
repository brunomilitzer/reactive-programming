package com.brunomilitzer.userservice.dto;

import com.brunomilitzer.userservice.enums.TransactionStatus;
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
