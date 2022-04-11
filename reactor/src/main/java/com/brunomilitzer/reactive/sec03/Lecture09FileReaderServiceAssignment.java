package com.brunomilitzer.reactive.sec03;

import com.brunomilitzer.reactive.sec03.assignment.FileReaderService;
import com.brunomilitzer.reactive.util.Util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Lecture09FileReaderServiceAssignment {

    public static void main(String[] args) {

        FileReaderService fileReaderService = new FileReaderService();
        Path path = Paths.get("reactor/src/main/resources/sec03/file01.txt");

        fileReaderService.read(path)
                .map(s -> {
                    Integer integer = Util.faker().random().nextInt(0, 10);

                    if (integer > 8) {
                        throw new RuntimeException("oops");
                    }

                    return s;

                })
                .take(20)
                .subscribe(Util.subscriber());

    }
}
