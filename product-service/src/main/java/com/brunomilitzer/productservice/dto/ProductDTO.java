package com.brunomilitzer.productservice.dto;

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
}
