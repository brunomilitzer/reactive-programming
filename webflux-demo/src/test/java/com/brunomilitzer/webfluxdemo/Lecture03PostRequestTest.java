package com.brunomilitzer.webfluxdemo;

import com.brunomilitzer.webfluxdemo.dto.MultiplyRequest;
import com.brunomilitzer.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lecture03PostRequestTest extends BaseTest {

    @Test
    public void postTest() {
        Mono<Response> responseMono = this.getWebClient().post().uri("reactive-math/multiply").bodyValue(buildRequest(5, 2))
                .retrieve().bodyToMono(Response.class).doOnNext(System.out::println);

        StepVerifier.create(responseMono).expectNextCount(1).verifyComplete();
    }

    private MultiplyRequest buildRequest(int a, int b) {
        MultiplyRequest multiplyRequest = new MultiplyRequest();
        multiplyRequest.setFirst(a);
        multiplyRequest.setSecond(b);

        return multiplyRequest;
    }
}
