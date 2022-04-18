package com.brunomilitzer.reactive.sec10;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lecture04RetryWhen {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {

        getIntegers().retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(3))).subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static Flux<Integer> getIntegers() {
        return Flux.range(1, 3).doOnSubscribe(s -> System.out.println("Subscribed"))
                .doOnComplete(() -> System.out.println("--Completed"))
                .map(i -> i / (Util.faker().random().nextInt(1, 5) > 3 ? 0 : 1))
                .doOnError(error -> System.out.println("--error"));
    }
}
