package com.brunomilitzer.reactive.sec11;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lecture07SinkReplay {

    public static void main(String[] args) {

        // handle through which we would push items
        Sinks.Many<Object> sink = Sinks.many().replay().all(); // cache behaviour

        // handle through subscribers will  receive items
        Flux<Object> flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");

        flux.subscribe(Util.subscriber("bob"));
        flux.subscribe(Util.subscriber("jack"));
        sink.tryEmitNext("?");
        flux.subscribe(Util.subscriber("elen"));

        sink.tryEmitNext("new msg");
    }
}
