package com.brunomilitzer.webfluxdemo.webclient;

import com.brunomilitzer.webfluxdemo.dto.InputFailedValidationResponse;
import com.brunomilitzer.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lecture06ExchangeTest extends BaseTest {

    // http://somesite/job/search?count=10&page=3

    // exchange = retrieve + additional info http status code
    @Test
    public void badRequestTest() {
        Mono<Object> responseMono = this.getWebClient().get()
                .uri("reactive-math/square/{input}/throw", 5).exchangeToMono(this::exchange)
                .doOnNext(System.out::println).doOnError(error -> System.out.println(error.getMessage()));

        StepVerifier.create(responseMono).expectNextCount(1).verifyComplete();
    }

    private Mono<Object> exchange(ClientResponse cr) {
        if (cr.rawStatusCode() == 400)
            return cr.bodyToMono(InputFailedValidationResponse.class);
        else return cr.bodyToMono(Response.class);
    }
}
