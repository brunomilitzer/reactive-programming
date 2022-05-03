package com.brunomilitzer.webfluxdemo.webtestclient;

import com.brunomilitzer.webfluxdemo.config.RequestHandler;
import com.brunomilitzer.webfluxdemo.config.RouterConfig;
import com.brunomilitzer.webfluxdemo.dto.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.ServerResponse;

@WebFluxTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = RouterConfig.class)
public class Lecture05RouterFunctionTest {

    private WebTestClient client;

    @MockBean
    private RequestHandler handler;

    private final ApplicationContext applicationContext;

    @Autowired
    public Lecture05RouterFunctionTest(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @BeforeAll
    public void setClient() {
        this.client = WebTestClient.bindToApplicationContext(applicationContext).build();
        WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
    }

    @Test
    public void routerTest() {
        Mockito.when(handler.squareHandler(Mockito.any())).thenReturn(ServerResponse.ok()
                .bodyValue(new Response(225)));

        this.client.get().uri("/router/square/{input}", 15).exchange().expectStatus().is2xxSuccessful()
                .expectBody(Response.class).value(r -> Assertions.assertThat(r.getOutput()).isEqualTo(225));
    }

}
