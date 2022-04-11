package com.brunomilitzer.reactive.sec03;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lecture07FluxGenerateCounter {

    public static void main(String[] args) {

        Flux.generate(
                () -> 1,
                (counter, sink) -> {
                    String country = Util.faker().country().name();
                    sink.next(country);

                    if (counter >= 10 || country.toLowerCase().equals("brazil"))
                        sink.complete();

                    return counter + 1;
                }
        )
                .take(4)
                .subscribe(Util.subscriber());
    }
}
