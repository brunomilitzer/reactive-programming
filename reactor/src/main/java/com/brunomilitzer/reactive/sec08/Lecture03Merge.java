package com.brunomilitzer.reactive.sec08;

import com.brunomilitzer.reactive.sec08.helper.Emirates;
import com.brunomilitzer.reactive.sec08.helper.Latam;
import com.brunomilitzer.reactive.sec08.helper.Qatar;
import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lecture03Merge {

    public static void main(String[] args) {

        Flux<String> merge = Flux.merge(
                Qatar.getFlights(),
                Emirates.getFlights(),
                Latam.getFlights()
        );

        merge.subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }
}
