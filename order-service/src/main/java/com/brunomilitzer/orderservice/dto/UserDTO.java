package com.brunomilitzer.orderservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDTO {

    private Long id;
    private String name;
    private Integer balance;
}
