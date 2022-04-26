package com.brunomilitzer.productservice.service;

import com.brunomilitzer.productservice.dto.ProductDTO;
import com.brunomilitzer.productservice.entity.Product;
import com.brunomilitzer.productservice.repository.ProductRepository;
import com.brunomilitzer.productservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Flux<ProductDTO> getAll() {
        return this.repository.findAll().map(EntityDtoUtil::toDTO);
    }

    public Mono<ProductDTO> getProductById(String id) {
        return this.repository.findById(id).map(EntityDtoUtil::toDTO);
    }

    public Flux<ProductDTO> getProductByPriceRange(BigDecimal min, BigDecimal max) {
        return this.repository.findByPriceBetween(Range.closed(min, max))
                .map(EntityDtoUtil::toDTO);
    }

    public Mono<ProductDTO> insertProduct(Mono<ProductDTO> productDTOMono) {
        return productDTOMono.map(EntityDtoUtil::toEntity).flatMap(this.repository::insert).map(EntityDtoUtil::toDTO);
    }

    public Mono<ProductDTO> updateProduct(String id, Mono<ProductDTO> productDTOMono) {
        return this.repository.findById(id).flatMap(p -> productDTOMono.map(EntityDtoUtil::toEntity)
                .doOnNext(e -> e.setId(id))).flatMap(this.repository::save).map(EntityDtoUtil::toDTO);
    }

    public Mono<Void> deleteProduct(String id) {
        return this.repository.deleteById(id);
    }
}
