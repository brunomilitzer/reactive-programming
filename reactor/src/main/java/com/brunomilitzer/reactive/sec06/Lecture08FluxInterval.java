package com.brunomilitzer.reactive.sec06;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Lecture08FluxInterval {

    public static void main(String[] args) {

        Flux.interval(Duration.ofSeconds(1)) // Interval has internal parallel and cannot be changed
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }
}
