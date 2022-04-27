package com.brunomilitzer.userservice.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@ToString
@Table("users")
public class User {

    @Id
    private Long id;
    private String name;
    private Integer balance;
}
