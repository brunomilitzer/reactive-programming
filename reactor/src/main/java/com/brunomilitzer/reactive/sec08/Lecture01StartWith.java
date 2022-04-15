package com.brunomilitzer.reactive.sec08;

import com.brunomilitzer.reactive.sec08.helper.NameGenerator;
import com.brunomilitzer.reactive.util.Util;

public class Lecture01StartWith {

    public static void main(String[] args) {

        NameGenerator generator = new NameGenerator();
        generator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("bob"));

        generator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("tom"));

        generator.generateNames()
                .filter(n -> n.startsWith("A"))
                .take(3)
                .subscribe(Util.subscriber("mike"));

    }
}
