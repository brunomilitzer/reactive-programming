package com.brunomilitzer.reactive.sec09;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lecture06Group {

    public static void main(String[] args) {

        Flux.range(1, 30).delayElements(Duration.ofSeconds(1)).groupBy(i -> i % 2) // key 0, 1
                .subscribe(gf -> process(gf, gf.key()));

        Util.sleepSeconds(60);
    }

    private static void process(Flux<Integer> flux, int key) {
        System.out.println("Called");
        flux.subscribe(i -> System.out.println("Key : " + key + ", Item : " + i));
    }
}
