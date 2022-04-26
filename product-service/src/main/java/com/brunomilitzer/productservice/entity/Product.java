package com.brunomilitzer.productservice.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
@Builder
@ToString
public class Product {

    @Id
    private String id;
    private String description;
    private BigDecimal price;
}
