package com.aiwa.reactivedemo.service;

import com.aiwa.reactivedemo.dto.MultiplyRequest;
import com.aiwa.reactivedemo.dto.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

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
//                .doOnNext(i -> Sleep.sleepSecond())
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.printf("Reactive processing element %s%n%n", i))
                .map(i -> new Response(i * value));
    }

    public Mono<Response> multiplicationOf(Mono<MultiplyRequest> requestMono) {
        return requestMono
                .map(request -> request.getA() * request.getB())
                .map(Response::new);
    }
}
