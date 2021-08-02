package com.aiwa.reactivedemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.net.URI;
import java.util.Map;

public class RetrieveRequestParametersTest extends BaseTestClass {

    @Autowired
    private WebClient mWebClient;

    @Test
    public void isQueryParametersAccessedTest() {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080/jobs/search?count={c}&page={p}")
                .build(10, 2);

        Flux<Integer> integerFlux = this.mWebClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(Integer.class)
                .doOnNext(System.out::println);

        StepVerifier.create(integerFlux)
                .expectNextCount(2L)
                .verifyComplete();
    }

    @Test
    public void isQueryParametersAccessedWithUriBuilderTest() {

        Flux<Integer> integerFlux = this.mWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/jobs/search")
                        .query("count={c}&page={p}")
                        .build(10, 3))
                .retrieve()
                .bodyToFlux(Integer.class)
                .doOnNext(System.out::println);

        StepVerifier.create(integerFlux)
                .expectNextCount(2L)
                .verifyComplete();
    }

    @Test
    public void isQueryParametersAccessedUsingMapToStoreParamsTest() {

        Map<String, Integer> integerMap = Map.of(
                "count", 10,
                "page", 2
        );

        Flux<Integer> integerFlux = this.mWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/jobs/search")
                        .query("count={count}&page={page}") // values in curly braces should match map's key names
                        .build(integerMap))
                .retrieve()
                .bodyToFlux(Integer.class)
                .doOnNext(System.out::println);

        StepVerifier.create(integerFlux)
                .expectNextCount(2L)
                .verifyComplete();
    }
}
