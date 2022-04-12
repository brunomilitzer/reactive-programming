package com.brunomilitzer.reactive.sec05;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lecture05HotPublishAutoConnect {

    public static void main(String[] args) {

        // share = publish().refCount(1)
        Flux<String> moviesStream = Flux.fromStream(() -> getMovie()).delayElements(Duration.ofSeconds(1))
                .publish().autoConnect(0);

        Util.sleepSeconds(3);

        moviesStream.subscribe(Util.subscriber("bruno"));

        Util.sleepSeconds(10);

        System.out.println("Vanessa is about to join");

        moviesStream.subscribe(Util.subscriber("vanessa"));

        Util.sleepSeconds(60);
    }

    // movie theatre
    private static Stream<String> getMovie() {
        System.out.println("Got the moving streaming req");
        return Stream.of(
                "Scene 1",
                "Scene 2",
                "Scene 3",
                "Scene 4",
                "Scene 5",
                "Scene 6",
                "Scene 7"
        );
    }
}
