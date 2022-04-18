package com.brunomilitzer.reactive.sec09;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lecture01Buffer {

    public static void main(String[] args) {

        eventStream()
                .buffer(5)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(300))
                .map(i -> "event" + i);
    }
}
