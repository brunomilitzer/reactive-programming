package com.brunomilitzer.orderservice.service;

import com.brunomilitzer.orderservice.client.ProductClient;
import com.brunomilitzer.orderservice.client.UserClient;
import com.brunomilitzer.orderservice.dto.PurchaseOrderRequestDTO;
import com.brunomilitzer.orderservice.dto.PurchaseOrderResponseDTO;
import com.brunomilitzer.orderservice.dto.RequestContext;
import com.brunomilitzer.orderservice.repository.PurchaseOrderRepository;
import com.brunomilitzer.orderservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class OrderFulfillmentService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    private final ProductClient productClient;

    private final UserClient userClient;

    @Autowired
    public OrderFulfillmentService(
            final PurchaseOrderRepository purchaseOrderRepository,
            final ProductClient productClient, final UserClient userClient) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.productClient = productClient;
        this.userClient = userClient;
    }

    public Mono<PurchaseOrderResponseDTO> processOrder(Mono<PurchaseOrderRequestDTO> requestDTOMono) {
        return requestDTOMono.map(RequestContext::new).flatMap(this::productRequestResponse)
                .doOnNext(EntityDtoUtil::setTransactionRequestDTO)
                .flatMap(this::userRequestResponse)
                .map(EntityDtoUtil::getPurchaseOrder).map(this.purchaseOrderRepository::save) // blocking
                .map(EntityDtoUtil::getPurchaseOrderResponseDTO).subscribeOn(Schedulers.boundedElastic());

    }

    private Mono<RequestContext> productRequestResponse(RequestContext rc) {
        return this.productClient.getProductById(rc.getPurchaseOrderRequestDTO().getProductId())
                .doOnNext(rc::setProductDTO).thenReturn(rc);
    }

    private Mono<RequestContext> userRequestResponse(RequestContext rc) {
        return this.userClient.authorizeTransaction(rc.getTransactionRequestDTO())
                .doOnNext(rc::setTransactionResponseDTO).thenReturn(rc);
    }
}
