package com.brunomilitzer.reactive.sec02;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lecture05FluxRange {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .log().map(i -> Util.faker().name().fullName()).log()
                .subscribe(Util.onNext()); // like a for loop
    }
}
