package com.brunomilitzer.reactive.sec03;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class Lecture06FluxGenerateAssignment {

    public static void main(String[] args) {

        // brazil
        // max = 10
        // subscriber cancels - exit

        AtomicInteger atomicInteger = new AtomicInteger(0);

        Flux.generate(synchronousSink -> {
            String country = Util.faker().country().name();
            System.out.println("emitting " + country);
            synchronousSink.next(country);
            atomicInteger.incrementAndGet();

            if (country.toLowerCase().endsWith("brazil")) {
                synchronousSink.complete();
            }

        }).subscribe(Util.subscriber());
    }
}
