package com.brunomilitzer.orderservice.controller;

import com.brunomilitzer.orderservice.dto.PurchaseOrderRequestDTO;
import com.brunomilitzer.orderservice.dto.PurchaseOrderResponseDTO;
import com.brunomilitzer.orderservice.service.OrderFulfillmentService;
import com.brunomilitzer.orderservice.service.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
public class PurchaseOrderController {

    private final OrderFulfillmentService orderFulfillmentService;

    private final OrderQueryService orderQueryService;

    @Autowired
    public PurchaseOrderController(
            final OrderFulfillmentService orderFulfillmentService, final OrderQueryService orderQueryService) {
        this.orderFulfillmentService = orderFulfillmentService;
        this.orderQueryService = orderQueryService;
    }

    @PostMapping
    public Mono<ResponseEntity<PurchaseOrderResponseDTO>> order(
            @RequestBody Mono<PurchaseOrderRequestDTO> requestDTOMono) {
        return this.orderFulfillmentService.processOrder(requestDTOMono).map(ResponseEntity::ok)
                .onErrorReturn(WebClientResponseException.class, ResponseEntity.badRequest().build())
                .onErrorReturn(WebClientRequestException.class,
                        ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build());
    }

    @GetMapping("user/{userId}")
    public Flux<PurchaseOrderResponseDTO> getOrdersByUserId(@PathVariable("userId") Long userId) {
        return this.orderQueryService.getProductsByUserId(userId);
    }

}
