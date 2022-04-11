package com.brunomilitzer.reactive.sec04;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lecture08DefaultIfEmpty {

    public static void main(String[] args) {

        getOrderNumbers().filter(i -> i > 10)
                .defaultIfEmpty(-100)
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> getOrderNumbers() {
        return Flux.range(1, 12);
    }
}
