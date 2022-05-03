package com.brunomilitzer.webfluxdemo.webtestclient;

import com.brunomilitzer.webfluxdemo.controller.ReactiveMathController;
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

@WebFluxTest(ReactiveMathController.class)
public class Lecture03ControllerPostTest {

    private final WebTestClient client;

    @MockBean
    private final ReactiveMathService reactiveMathService;

    @Autowired
    public Lecture03ControllerPostTest(WebTestClient client, ReactiveMathService reactiveMathService) {
        this.client = client;
        this.reactiveMathService = reactiveMathService;
    }

    @Test
    public void postTest() {
        Mockito.when(reactiveMathService.multiply(Mockito.any())).thenReturn(Mono.just(new Response(1)));

        this.client.post().uri("/reactive-math/multiply").accept(MediaType.APPLICATION_JSON)
                .headers(h -> h.setBasicAuth("username", "password"))
                .headers(h -> h.set("somekey", "somevalue")).bodyValue(new MultiplyRequest()).exchange()
                .expectStatus().is2xxSuccessful();
    }
}
