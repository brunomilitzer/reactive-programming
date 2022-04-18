package com.brunomilitzer.reactive.sec11;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lecture04SinkMulticast {

    public static void main(String[] args) {

        // handle through which we would push items
        Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();

        // handle through subscribers will  receive items
        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("bob"));
        flux.subscribe(Util.subscriber("jack"));

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");
    }
}
