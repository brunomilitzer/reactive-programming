package com.brunomilitzer.webfluxdemo.controller;

import com.brunomilitzer.webfluxdemo.dto.MultiplyRequest;
import com.brunomilitzer.webfluxdemo.dto.Response;
import com.brunomilitzer.webfluxdemo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathController {

    private ReactiveMathService mathService;

    @Autowired
    public ReactiveMathController(ReactiveMathService mathService) {
        this.mathService = mathService;
    }

    @GetMapping("square/{input}")
    public Mono<Response> findSquare(@PathVariable int input) {
        return this.mathService.findSquare(input).defaultIfEmpty(new Response(-1));
    }

    @GetMapping("table/{input}")
    public Flux<Response> multiplicationTable(@PathVariable int input ){
        return this.mathService.multiplicationTable(input);
    }

    @GetMapping(value = "table/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> multiplicationTableStream(@PathVariable int input ){
        return this.mathService.multiplicationTable(input);
    }

    @PostMapping("multiply")
    private Mono<Response> multiply(@RequestBody Mono<MultiplyRequest> requestMono,
                                    @RequestHeader Map<String, String> headers) {
        System.out.println(headers);
        return this.mathService.multiply(requestMono);
    }
}
