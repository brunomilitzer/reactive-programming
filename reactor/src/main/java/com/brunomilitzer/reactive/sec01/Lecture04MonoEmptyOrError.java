package com.brunomilitzer.reactive.sec01;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Mono;

public class Lecture04MonoEmptyOrError {

    public static void main(String[] args) {

        userRepository(20).subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete());
    }

    private static Mono<String> userRepository(int userId) {
        // 1
        if (userId == 1) {
            return Mono.just(Util.faker().name().firstName());
        }
        else if (userId == 2) {
            return Mono.empty(); // null
        }
        else {
            return Mono.error(new RuntimeException("Not ain the allowed range"));
        }
    }
}
