package com.brunomilitzer.reactive.sec02;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lecture01FluxIntro {

    public static void main(String[] args) {

        Flux<Object> flux = Flux.just(1, 2, 3, 4, "a", Util.faker().name().fullName()); // Flux.empty();

        flux.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }
}
