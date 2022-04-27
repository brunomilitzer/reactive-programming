package com.brunomilitzer.userservice.repository;

import com.brunomilitzer.userservice.entity.UserTransaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserTransactionRepository extends ReactiveCrudRepository<UserTransaction, Long> {

    Flux<UserTransaction> findByUserId(Long userId);
}
