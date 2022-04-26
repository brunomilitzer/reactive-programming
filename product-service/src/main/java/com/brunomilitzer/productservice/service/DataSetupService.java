package com.brunomilitzer.productservice.service;

import com.brunomilitzer.productservice.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DataSetupService implements CommandLineRunner {

    private ProductService productService;

    @Autowired
    public DataSetupService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        ProductDTO product1 = ProductDTO.builder().description("Logitech Webcam").price(90).build();
        ProductDTO product2 = ProductDTO.builder().description("Panasonic TV 4K").price(700).build();
        ProductDTO product3 = ProductDTO.builder().description("Nikon SLR Camera").price(950).build();
        ProductDTO product4 = ProductDTO.builder().description("Samsung Galaxy S20+").price(1250).build();

        Flux.just(product1, product2, product3, product4)
                .flatMap(p -> this.productService.insertProduct(Mono.just(p))).subscribe(System.out::println);
    }
}
