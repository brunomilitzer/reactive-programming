package com.brunomilitzer.webfluxdemo;

import com.brunomilitzer.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lecture02GetMultiResponseTest extends BaseTest {

    @Test
    public void fluxTest() {
        Flux<Response> responseFlux = this.getWebClient().get().uri("reactive-math/table/{input}", 5)
                .retrieve().bodyToFlux(Response.class).doOnNext(System.out::println);

        StepVerifier.create(responseFlux).expectNextCount(10).verifyComplete();
    }

    @Test
    public void fluxStreamTest() {
        Flux<Response> responseFlux = this.getWebClient().get().uri("reactive-math/table/{input}/stream", 5)
                .retrieve().bodyToFlux(Response.class).doOnNext(System.out::println);

        StepVerifier.create(responseFlux).expectNextCount(10).verifyComplete();
    }
}
