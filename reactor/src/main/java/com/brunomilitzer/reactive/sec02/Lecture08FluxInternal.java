package com.brunomilitzer.reactive.sec02;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lecture08FluxInternal {

    public static void main(String[] args) {

        Flux.interval(Duration.ofSeconds(1))
                .subscribe(Util.onNext());

        Util.sleepSeconds(5);
    }
}
