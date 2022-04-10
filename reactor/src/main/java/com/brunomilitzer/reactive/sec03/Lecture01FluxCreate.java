package com.brunomilitzer.reactive.sec03;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lecture01FluxCreate {

    public static void main(String[] args) {

        Flux.create(fluxSink -> {

            String country;
            do {
                country = Util.faker().country().name();
                fluxSink.next(country);
            } while (!country.toLowerCase().startsWith("brazil"));

            fluxSink.complete();

        }).subscribe(Util.subscriber());
    }
}
