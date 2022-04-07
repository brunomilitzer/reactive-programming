package com.brunomilitzer.reactive.sec01;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Mono;

public class Lecture08MonoFromRunnable {

    public static void main(String[] args) {

        Mono.fromRunnable(timeConsumingProcess()).subscribe(
                Util.onNext(),
                Util.onError(),
                () -> System.out.println("process is done. Sending emails..."));
    }

    private static Runnable timeConsumingProcess( ){
        return () -> {
            Util.sleepSeconds(3);
            System.out.println("Operations completed");
        };
    }
}
