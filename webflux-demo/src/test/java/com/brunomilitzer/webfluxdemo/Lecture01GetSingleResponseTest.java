package com.brunomilitzer.webfluxdemo;

import com.brunomilitzer.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lecture01GetSingleResponseTest extends BaseTest {

    @Test
    public void blockTest() {
        Response response = this.getWebClient().get().uri("reactive-math/square/{input}", 5).retrieve()
                .bodyToMono(Response.class).block();

        System.out.println(response);
    }

    @Test
    public void stepVerifierTest() {
        Mono<Response> responseMono = this.getWebClient().get().uri("reactive-math/square/{input}", 5).retrieve()
                .bodyToMono(Response.class);

        StepVerifier.create(responseMono).expectNextMatches(r -> r.getOutput() == 25).verifyComplete();
    }
}
