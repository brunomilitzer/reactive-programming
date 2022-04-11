package com.brunomilitzer.reactive.sec04;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lecture04LimitRate {

    public static void main(String[] args) {

        Flux.range(1, 1000)
                .log()
                .limitRate(100, 99) // 75% by default 0 for 100% and 99 for 99% 100 will be for 75%
                .subscribe(Util.subscriber());
    }
}
