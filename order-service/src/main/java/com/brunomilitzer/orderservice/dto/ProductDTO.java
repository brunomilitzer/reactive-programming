package com.brunomilitzer.orderservice.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String id;
    private String description;
    private Integer price;

    public ProductDTO(String description, Integer price) {
        this.description = description;
        this.price = price;
    }
}
