package com.brunomilitzer.webfluxdemo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lecture09AssignmentTest extends BaseTest {

    private final static String FORMAT = "%d %s %d = %s";
    public static final int A = 10;

    @Test
    public void test() {
        Flux<String> flux = Flux.range(1, 5)
                .flatMap(b -> Flux.just("+", "-", "*", "/").flatMap(op -> send(b, op)))
                .doOnNext(System.out::println);

        StepVerifier.create(flux).expectNextCount(20).verifyComplete();
    }

    private Mono<String> send(int b, String op) {
        return this.getWebClient().get().uri("calculator/{a}/{b}", A, b).headers(h -> h.set("OP", op))
                .retrieve().bodyToMono(String.class).map(v -> String.format(FORMAT, A, op, b, v));
    }

}
