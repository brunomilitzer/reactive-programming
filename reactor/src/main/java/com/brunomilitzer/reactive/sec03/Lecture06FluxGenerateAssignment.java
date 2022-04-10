package com.brunomilitzer.reactive.sec03;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lecture06FluxGenerateAssignment {

    public static void main(String[] args) {

        Flux.generate(synchronousSink -> {
            String country = Util.faker().country().name();
            System.out.println("emitting " + country);
            synchronousSink.next(country);

            if (country.toLowerCase().endsWith("brazil")) {
                synchronousSink.complete();
            }

        }).subscribe(Util.subscriber());
    }
}
