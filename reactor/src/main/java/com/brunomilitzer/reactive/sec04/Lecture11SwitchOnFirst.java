package com.brunomilitzer.reactive.sec04;

import com.brunomilitzer.reactive.sec04.helper.Person;
import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class Lecture11SwitchOnFirst {

    public static void main(String[] args) {

        getPerson()
                .switchOnFirst(((signal, personFlux) -> {
                    System.out.println("inside switchOnFirst");
                    return signal.isOnNext() && signal.get().getAge() > 17
                            ? personFlux : applyFilterMap().apply(personFlux);
                }))
                .subscribe(Util.subscriber());
    }

    public static Flux<Person> getPerson() {
        return Flux.range(1, 10)
                .map(i -> new Person());

    }

    public static Function<Flux<Person>, Flux<Person>> applyFilterMap() {
        return flux -> flux.filter(p -> p.getAge() > 17)
                .doOnNext(p -> p.setName(p.getName().toUpperCase()))
                .doOnDiscard(Person.class, p -> System.out.println("Not allowing : " + p));
    }
}
