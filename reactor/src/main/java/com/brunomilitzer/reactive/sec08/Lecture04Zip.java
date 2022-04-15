package com.brunomilitzer.reactive.sec08;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lecture04Zip {

    public static void main(String[] args) {

        Flux.zip(getBody(), getEngine(), getTires())
                .subscribe(Util.subscriber());
    }

    private static Flux<String> getBody() {
        return Flux.range(1, 5).map(i -> "body");
    }

    private static Flux<String> getEngine() {
        return Flux.range(1, 3).map(i -> "engine");
    }

    private static Flux<String> getTires() {
        return Flux.range(1, 6).map(i -> "tires");
    }
}
