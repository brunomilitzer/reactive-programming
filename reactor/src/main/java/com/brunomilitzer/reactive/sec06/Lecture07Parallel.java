package com.brunomilitzer.reactive.sec06;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class Lecture07Parallel {

    public static void main(String[] args) {

        //List<Integer> list = new ArrayList<>(); // ArrayList not thread safe

        Flux.range(1, 10)
                .parallel(10)
                .runOn(Schedulers.boundedElastic())
                .doOnNext(i -> printThreadName("next " + i))
                .sequential()
                //.subscribe(list::add);
                .subscribe(v -> printThreadName("sub " + v));


        Util.sleepSeconds(5);
        //System.out.println(list.size());
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
    }
}
