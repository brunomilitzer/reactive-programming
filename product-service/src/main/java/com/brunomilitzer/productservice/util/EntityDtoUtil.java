package com.brunomilitzer.productservice.util;

import com.brunomilitzer.productservice.dto.ProductDTO;
import com.brunomilitzer.productservice.entity.Product;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static ProductDTO toDTO(Product product) {
        ProductDTO dto = ProductDTO.builder().build();
        BeanUtils.copyProperties(product, dto);

        return dto;
    }

    public static Product toEntity(ProductDTO dto) {
        Product product = Product.builder().build();
        BeanUtils.copyProperties(dto, product);
        return product;
    }
}
