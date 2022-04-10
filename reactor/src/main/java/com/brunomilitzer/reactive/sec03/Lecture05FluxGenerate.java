package com.brunomilitzer.reactive.sec03;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lecture05FluxGenerate {

    public static void main(String[] args) {

        Flux.generate(synchronousSink -> {
            System.out.println("emitting");
            synchronousSink.next(Util.faker().country().name()); // emmit only one item for FluxSync
            //synchronousSink.error(new RuntimeException("oops"));
            //synchronousSink.complete();

        })
                .take(2)
                .subscribe(Util.subscriber());
    }
}
