package com.aiwa.reactivedemo.service;

import com.aiwa.reactivedemo.dto.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveMathService {

    public Mono<Response> square(final Integer value) {
        return Mono
                .fromSupplier(() -> value * value)
                .map(Response::new);
    }

    public Flux<Response> table(final Integer value) {
        return Flux
                .range(1, 12)
                .doOnNext(i -> Sleep.sleepSecond())
                .doOnNext(i -> System.out.printf("Reactive processing element %s%n%n", i))
                .map(i -> new Response(i * value));
    }

}
