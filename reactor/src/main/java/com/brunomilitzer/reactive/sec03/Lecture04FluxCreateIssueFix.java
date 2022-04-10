package com.brunomilitzer.reactive.sec03;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lecture04FluxCreateIssueFix {

    public static void main(String[] args) {

        // only one instance of fluxSink
        Flux.create(fluxSink -> {

            String country;
            do {
                country = Util.faker().country().name();
                System.out.println("emitting : " + country);
                fluxSink.next(country);
            } while (!country.toLowerCase().startsWith("brazil") && !fluxSink.isCancelled());

            fluxSink.complete();

        })
        .take(3)
        .subscribe(Util.subscriber());
    }
}
