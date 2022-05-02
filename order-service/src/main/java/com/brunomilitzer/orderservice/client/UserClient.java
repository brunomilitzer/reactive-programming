package com.brunomilitzer.orderservice.client;

import com.brunomilitzer.orderservice.dto.TransactionRequestDTO;
import com.brunomilitzer.orderservice.dto.TransactionResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserClient {

    private final WebClient webClient;

    public UserClient(@Value("${user.service.url}") String url) {
        this.webClient = WebClient.builder().baseUrl(url).build();
    }

    public Mono<TransactionResponseDTO> authorizeTransaction(TransactionRequestDTO requestDTO) {
        return this.webClient.post().uri("transaction").bodyValue(requestDTO)
                .retrieve().bodyToMono(TransactionResponseDTO.class);
    }
}
