package com.brunomilitzer.reactive.sec04;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lecture02HandleAssignment {

    public static void main(String[] args) {

        Flux.generate(synchronousSink -> synchronousSink.next(Util.faker().country().name()))
                .map(Object::toString)
                .handle((s, synchronousSink) -> {
                    synchronousSink.next(s);

                    if (s.toLowerCase().equals("brazil")) synchronousSink.complete();
                }).subscribe(Util.subscriber());
    }
}
