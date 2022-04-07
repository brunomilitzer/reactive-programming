package com.brunomilitzer.reactive.sec01;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Lecture07MonoFromFuture {

    public static void main(String[] args) {

        Mono.fromFuture(getName()).subscribe(Util.onNext());

        Util.sleepSeconds(1);
    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> Util.faker().name().fullName());
    }
}
