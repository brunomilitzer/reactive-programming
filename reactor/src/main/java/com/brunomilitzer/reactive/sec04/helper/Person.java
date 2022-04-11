package com.brunomilitzer.reactive.sec04.helper;

import com.brunomilitzer.reactive.util.Util;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Person {

    private String name;
    private Integer age;

    public Person() {
        this.name = Util.faker().name().firstName();
        this.age = Util.faker().random().nextInt(1, 30);
    }
}
