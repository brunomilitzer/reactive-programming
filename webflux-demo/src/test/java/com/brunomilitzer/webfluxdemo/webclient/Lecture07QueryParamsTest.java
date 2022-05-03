package com.brunomilitzer.webfluxdemo.webclient;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Map;

public class Lecture07QueryParamsTest extends BaseTest{

    private String queryString = "http://localhost:8080/jobs/search?count={count}&page={page}";

    @Test
    public void queryParamsTest() {
        Map<String, Integer> map = Map.of("count", 10, "page", 20);

        /*URI uri = UriComponentsBuilder.fromUriString(queryString).build(10, 20);*/

        Flux<Integer> integerFlux = this.getWebClient().get().uri(b -> b.path("jobs/search")
                        .query("count={count}&page={page}").build(map))
                .retrieve().bodyToFlux(Integer.class).doOnNext(System.out::println);

        StepVerifier.create(integerFlux).expectNextCount(2).verifyComplete();
    }
}
