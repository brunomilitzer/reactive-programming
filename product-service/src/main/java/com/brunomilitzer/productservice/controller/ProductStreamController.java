package com.brunomilitzer.productservice.controller;

import com.brunomilitzer.productservice.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("product")
public class ProductStreamController {

    private final Flux<ProductDTO> flux;

    @Autowired
    public ProductStreamController(Flux<ProductDTO> flux) {
        this.flux = flux;
    }

    @GetMapping(value = "stream/{maxPrice}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProductDTO> getProductsUpdates(@PathVariable("maxPrice") Integer maxPrice) {
        return this.flux.filter(dto -> dto.getPrice() <= maxPrice);
    }
}
