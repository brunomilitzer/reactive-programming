package com.brunomilitzer.webfluxdemo.webtestclient;

import com.brunomilitzer.webfluxdemo.dto.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lecture01SimpleWebTestClientTest extends BaseTest {

    @Test
    public void stepVerifierTest() {
        Flux<Response> responseMono = this.getWebTestClient().get()
                .uri("/reactive-math/square/{input}", 5).exchange().expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON).returnResult(Response.class).getResponseBody();

        StepVerifier.create(responseMono).expectNextMatches(r -> r.getOutput() == 25).verifyComplete();
    }

    @Test
    public void fluentAssertionTest() {
        this.getWebTestClient().get()
                .uri("/reactive-math/square/{input}", 5).exchange().expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody(Response.class)
                .value(r -> Assertions.assertThat(r.getOutput()).isEqualTo(25));
    }
}
