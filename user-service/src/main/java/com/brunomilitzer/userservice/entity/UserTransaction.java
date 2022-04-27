package com.brunomilitzer.userservice.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
public class UserTransaction {

    @Id
    private Long id;
    private Long userId;
    private Integer amount;
    private LocalDateTime transactionDate;
}
