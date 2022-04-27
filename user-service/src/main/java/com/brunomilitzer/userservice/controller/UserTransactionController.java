package com.brunomilitzer.userservice.controller;

import com.brunomilitzer.userservice.dto.TransactionRequestDTO;
import com.brunomilitzer.userservice.dto.TransactionResponseDTO;
import com.brunomilitzer.userservice.entity.UserTransaction;
import com.brunomilitzer.userservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user/transaction")
public class UserTransactionController {

    private final TransactionService transactionService;

    @Autowired
    public UserTransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Mono<TransactionResponseDTO> createTransaction(@RequestBody Mono<TransactionRequestDTO> requestDTOMono) {
        return requestDTOMono.flatMap(this.transactionService::createTransaction);
    }

    @GetMapping
    public Flux<UserTransaction> getByUserId(@RequestParam("userId") Long userId) {
        return this.transactionService.getByUserId(userId);
    }
}
