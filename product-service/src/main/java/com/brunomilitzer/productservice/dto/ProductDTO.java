package com.brunomilitzer.productservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Builder
@ToString
public class ProductDTO {

    private String id;
    private String description;
    private BigDecimal price;
}
