package com.brunomilitzer.userservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class TransactionRequestDTO {

    private Long userId;
    private Integer amount;
}
