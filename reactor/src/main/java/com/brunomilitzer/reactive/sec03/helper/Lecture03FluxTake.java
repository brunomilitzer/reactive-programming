package com.brunomilitzer.reactive.sec03.helper;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lecture03FluxTake {

    public static void main(String[] args) {

        // log
        // map
        // filter
        Flux.range(1, 10)
                .log()
                .take(3) // cancels
                .log()
                .subscribe(Util.subscriber());
    }
}
