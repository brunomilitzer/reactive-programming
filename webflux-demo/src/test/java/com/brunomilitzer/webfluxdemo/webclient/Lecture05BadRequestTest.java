package com.brunomilitzer.webfluxdemo.webclient;

import com.brunomilitzer.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lecture05BadRequestTest extends BaseTest {

    @Test
    public void badRequestTest() {
        Mono<Response> responseMono = this.getWebClient().get()
                .uri("reactive-math/square/{input}/throw", 5).retrieve().bodyToMono(Response.class)
                .doOnNext(System.out::println).doOnError(error -> System.out.println(error.getMessage()));

        StepVerifier.create(responseMono).verifyError(WebClientResponseException.BadRequest.class);
    }
}
