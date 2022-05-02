package com.brunomilitzer.orderservice.controller;

import com.brunomilitzer.orderservice.dto.PurchaseOrderRequestDTO;
import com.brunomilitzer.orderservice.dto.PurchaseOrderResponseDTO;
import com.brunomilitzer.orderservice.service.OrderFulfillmentService;
import com.brunomilitzer.orderservice.service.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public Mono<PurchaseOrderResponseDTO> order(@RequestBody Mono<PurchaseOrderRequestDTO> requestDTOMono) {
        return this.orderFulfillmentService.processOrder(requestDTOMono);
    }

    @GetMapping("user/{userId}")
    public Flux<PurchaseOrderResponseDTO> getOrdersByUserId(@PathVariable("userId") Long userId) {
        return this.orderQueryService.getProductsByUserId(userId);
    }

}
