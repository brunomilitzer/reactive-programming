package com.brunomilitzer.orderservice.service;

import com.brunomilitzer.orderservice.dto.PurchaseOrderResponseDTO;
import com.brunomilitzer.orderservice.entity.PurchaseOrder;
import com.brunomilitzer.orderservice.repository.PurchaseOrderRepository;
import com.brunomilitzer.orderservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class OrderQueryService {

    private final PurchaseOrderRepository orderRepository;

    @Autowired
    public OrderQueryService(final PurchaseOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Flux<PurchaseOrderResponseDTO> getProductsByUserId(Long userId) {
        return Flux.fromStream(() -> this.orderRepository.findByUserId(userId).stream()) // blocking
                .map(EntityDtoUtil::getPurchaseOrderResponseDTO).subscribeOn(Schedulers.boundedElastic());
    }
}
