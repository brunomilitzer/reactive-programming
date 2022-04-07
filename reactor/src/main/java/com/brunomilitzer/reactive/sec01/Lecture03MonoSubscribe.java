package com.brunomilitzer.reactive.sec01;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Mono;

public class Lecture03MonoSubscribe {

    public static void main(String[] args) {

        // publisher
        Mono<Integer> mono = Mono.just("ball").map(String::length).map(l -> l / 0 );

        // 1
        //mono.subscribe();

        // 2
        mono.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
        //mono.subscribe(System.out::println);
    }
}
