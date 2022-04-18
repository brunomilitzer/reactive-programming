package com.brunomilitzer.reactive.sec11;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class Lecture06SinkMultiDirectAll {

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");

        // handle through which we would push items
        Sinks.Many<Object> sink = Sinks.many().multicast().directAllOrNothing();
        //Sinks.Many<Object> sink = Sinks.many().multicast().directBestEffort();

        // handle through subscribers will  receive items
        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("bob"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("jack"));

        for (int i = 0; i < 1000; i++) {
            sink.tryEmitNext(i);
        }

        Util.sleepSeconds(10);
    }
}
