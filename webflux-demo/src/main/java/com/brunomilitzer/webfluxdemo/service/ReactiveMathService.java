package com.brunomilitzer.webfluxdemo.service;

import com.brunomilitzer.webfluxdemo.dto.MultiplyRequest;
import com.brunomilitzer.webfluxdemo.dto.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ReactiveMathService {

    public Mono<Response> findSquare(int input) {
        return Mono.fromSupplier(() -> input * input).map(Response::new);
    }

    public Flux<Response> multiplicationTable(int input) {
        /*List<Response> list =  IntStream.range(1, 10)
                .peek(i -> SleepUtil.sleepSeconds(1))
                .peek(i -> System.out.println("math-service processing : " + i))
                .mapToObj(i -> new Response(i * input)).collect(Collectors.toList());

        return Flux.fromIterable(list);*/ // not reactive
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                //.doOnNext(i -> SleepUtil.sleepSeconds(1))
                .doOnNext(i -> System.out.println("reactive-math-service process : " + i))
                .map(i -> new Response(i * input));
    }

    public Mono<Response> multiply(Mono<MultiplyRequest> multiplyRequestMono) {
        return multiplyRequestMono.map(v -> v.getFirst() * v.getSecond()).map(Response::new);
    }
}
