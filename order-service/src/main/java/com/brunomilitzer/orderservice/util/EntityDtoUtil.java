package com.brunomilitzer.orderservice.util;

import com.brunomilitzer.orderservice.dto.PurchaseOrderResponseDTO;
import com.brunomilitzer.orderservice.dto.RequestContext;
import com.brunomilitzer.orderservice.dto.TransactionRequestDTO;
import com.brunomilitzer.orderservice.entity.PurchaseOrder;
import com.brunomilitzer.orderservice.enums.OrderStatus;
import com.brunomilitzer.orderservice.enums.TransactionStatus;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static PurchaseOrderResponseDTO getPurchaseOrderResponseDTO(PurchaseOrder purchaseOrder) {
        PurchaseOrderResponseDTO dto = PurchaseOrderResponseDTO.builder().orderId(purchaseOrder.getId()).build();
        BeanUtils.copyProperties(purchaseOrder, dto);

        return dto;
    }

    public static void setTransactionRequestDTO(RequestContext requestContext) {
        TransactionRequestDTO dto = new TransactionRequestDTO();
        dto.setUserId(requestContext.getPurchaseOrderRequestDTO().getUserId());
        dto.setAmount(requestContext.getProductDTO().getPrice());

        requestContext.setTransactionRequestDTO(dto);
    }

    public static PurchaseOrder getPurchaseOrder(RequestContext requestContext) {
        return PurchaseOrder.builder().userId(requestContext.getTransactionRequestDTO().getUserId())
                .productId(requestContext.getPurchaseOrderRequestDTO().getProductId())
                .amount(requestContext.getProductDTO().getPrice())
                .status(getOrderStatus(requestContext)).build();
    }

    private static OrderStatus getOrderStatus(RequestContext requestContext) {
        TransactionStatus status = requestContext.getTransactionResponseDTO().getStatus();
        return TransactionStatus.APPROVED.equals(status) ? OrderStatus.COMPLETED : OrderStatus.FAILED;
    }
}
