package com.brunomilitzer.reactive.sec02;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lecture09FluxFromMono {

    public static void main(String[] args) {

        /*Mono<String> mono = Mono.just("a");

        Flux<String> flux = Flux.from(mono);

        flux.subscribe(Util.onNext());*/

        //doSomething(flux);

        Flux.range(1, 10).next()
                .filter(i -> i > 3)
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        Util.onComplete());
    }

    private static void doSomething(Flux<String> flux) {

    }
}
