package com.brunomilitzer.reactive.sec11;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class Lecture01SinkOne {

    public static void main(String[] args) {

        // mono 1 value / empty / error
        Sinks.One<Object> sink = Sinks.one();

        Mono<Object> mono = sink.asMono();

        mono.subscribe(Util.subscriber("bob"));
        mono.subscribe(Util.subscriber("jack"));

        //sink.tryEmitValue("hi");
        //sink.tryEmitEmpty();
        //sink.tryEmitError(new RuntimeException("error"));

        /*sink.emitValue("hi", (signalType, emitResult) -> {
            System.out.println(signalType.name());
            System.out.println(emitResult.name());
            return false;
        });*/

        /*sink.emitValue("hello", (signalType, emitResult) -> {
            System.out.println(signalType.name());
            System.out.println(emitResult.name());
            return false;
        });*/

        sink.tryEmitValue("hello");
    }
}
