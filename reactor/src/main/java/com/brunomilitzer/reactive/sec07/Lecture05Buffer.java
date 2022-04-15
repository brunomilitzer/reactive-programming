package com.brunomilitzer.reactive.sec07;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

public class Lecture05Buffer {

    public static void main(String[] args) {

        // 75% 12
        System.setProperty("reactor.bufferSize.small", "16");

        Flux.create(fluxSink -> {
                    for (int i = 1; i < 201 && !fluxSink.isCancelled(); i++) {
                        fluxSink.next(i);
                        System.out.println("Pushed : " + i);
                        Util.sleepMillis(1);
                    }
                    fluxSink.complete();
                }, FluxSink.OverflowStrategy.BUFFER)
                .onBackpressureBuffer(50, o -> System.out.println("Dropped : " + o))
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    Util.sleepMillis(10);
                }).subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }

}
