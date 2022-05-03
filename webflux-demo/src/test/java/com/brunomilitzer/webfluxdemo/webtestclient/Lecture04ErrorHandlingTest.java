package com.brunomilitzer.webfluxdemo.webtestclient;

import com.brunomilitzer.webfluxdemo.controller.ReactiveMathValidationController;
import com.brunomilitzer.webfluxdemo.dto.MultiplyRequest;
import com.brunomilitzer.webfluxdemo.dto.Response;
import com.brunomilitzer.webfluxdemo.service.ReactiveMathService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest(ReactiveMathValidationController.class)
public class Lecture04ErrorHandlingTest {

    private final WebTestClient client;

    @MockBean
    private final ReactiveMathService reactiveMathService;

    @Autowired
    public Lecture04ErrorHandlingTest(WebTestClient client, ReactiveMathService reactiveMathService) {
        this.client = client;
        this.reactiveMathService = reactiveMathService;
    }

    @Test
    public void errorHandlingTest() {
        Mockito.when(reactiveMathService.findSquare(Mockito.anyInt())).thenReturn(Mono.just(new Response(1)));

        this.client.get().uri("/reactive-math/square/{number}/throw", 5).exchange()
                .expectStatus().isBadRequest().expectBody().jsonPath("$.message")
                .isEqualTo("allowed range is 10 - 20").jsonPath("$.errorCode")
                .isEqualTo(100).jsonPath("$.input").isEqualTo(5);

    }
}
