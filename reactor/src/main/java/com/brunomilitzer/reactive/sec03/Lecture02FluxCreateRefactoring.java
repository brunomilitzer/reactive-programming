package com.brunomilitzer.reactive.sec03;

import com.brunomilitzer.reactive.sec03.helper.NameProducer;
import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lecture02FluxCreateRefactoring {

    public static void main(String[] args) {

        NameProducer nameProducer = new NameProducer();
        Flux.create(nameProducer).subscribe(Util.subscriber());

        Runnable runnable = nameProducer::produce;

        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }

        Util.sleepSeconds(2);
    }
}
