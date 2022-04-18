package com.brunomilitzer.reactive.sec09;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lecture02BufferTimeout {

    public static void main(String[] args) {

        eventStream()
                .bufferTimeout(5, Duration.ofSeconds(2))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(800))
                .map(i -> "event" + i);
    }
}
