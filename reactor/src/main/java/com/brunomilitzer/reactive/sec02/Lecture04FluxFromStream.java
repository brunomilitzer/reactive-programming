package com.brunomilitzer.reactive.sec02;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class Lecture04FluxFromStream {

    public static void main(String[] args) {

        List<Integer> list = List.of(1, 2, 3, 4, 5);

        Stream<Integer> stream = list.stream();

        // stream.forEach(System.out::println); // closed
        // stream.forEach(System.out::println);

        Flux<Integer> integerFlux = Flux.fromStream(() -> list.stream());

        integerFlux.subscribe(Util.onNext(), Util.onError(), Util.onComplete()); // closed
        integerFlux.subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }
}
