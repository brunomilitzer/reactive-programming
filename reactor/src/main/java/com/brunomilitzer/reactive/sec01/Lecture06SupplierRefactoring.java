package com.brunomilitzer.reactive.sec01;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Lecture06SupplierRefactoring {

    public static void main(String[] args) {

        getName();
        //getName().subscribe(Util.onNext()); // Blocking
        //getName().subscribeOn(Schedulers.boundedElastic()).subscribe(Util.onNext());
        String name = getName().subscribeOn(Schedulers.boundedElastic()).block();
        System.out.println(name);
        getName();

        Util.sleepSeconds(4);
    }

    public static Mono<String> getName() {
        System.out.println("entered getName method");
        return Mono.fromSupplier(() -> {
            System.out.println("Generating name...");
            Util.sleepSeconds(3);
            return Util.faker().name().fullName();
        }).map(String::toUpperCase);
    }
}
