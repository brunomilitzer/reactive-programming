package com.brunomilitzer.userservice.service;

import com.brunomilitzer.userservice.dto.TransactionRequestDTO;
import com.brunomilitzer.userservice.dto.TransactionResponseDTO;
import com.brunomilitzer.userservice.entity.UserTransaction;
import com.brunomilitzer.userservice.enums.TransactionStatus;
import com.brunomilitzer.userservice.repository.UserRepository;
import com.brunomilitzer.userservice.repository.UserTransactionRepository;
import com.brunomilitzer.userservice.util.EntityDTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionService {

    private UserRepository userRepository;

    private UserTransactionRepository userTransactionRepository;

    @Autowired
    public TransactionService(UserRepository userRepository, UserTransactionRepository userTransactionRepository) {
        this.userRepository = userRepository;
        this.userTransactionRepository = userTransactionRepository;
    }

    public Mono<TransactionResponseDTO> createTransaction(final TransactionRequestDTO requestDTO) {
        return this.userRepository.updateUserBalance(requestDTO.getUserId(), requestDTO.getAmount())
                .filter(Boolean::booleanValue).map(b -> EntityDTOUtil.toEntity(requestDTO))
                .flatMap(this.userTransactionRepository::save)
                .map(ut -> EntityDTOUtil.toDTO(requestDTO, TransactionStatus.APPROVED))
                .defaultIfEmpty(EntityDTOUtil.toDTO(requestDTO, TransactionStatus.DECLINED));

    }

    public Flux<UserTransaction> getByUserId(Long userId) {
        return this.userTransactionRepository.findByUserId(userId);
    }
}
