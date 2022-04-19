package com.brunomilitzer.reactive.sec12;

import com.brunomilitzer.reactive.util.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Lecture01Context {

    public static void main(String[] args) {

        getWelcomeMessage()
                .contextWrite(ctx -> ctx.put("user", ctx.get("user").toString().toUpperCase()))
                .contextWrite(Context.of("user", "bob"))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
            if (ctx.hasKey("user")) return Mono.just("Welcome " + ctx.get("user"));

            return Mono.error(new RuntimeException("unauthenticated"));
        });
    }
}
