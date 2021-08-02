package com.aiwa.reactivedemo;

import com.aiwa.reactivedemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class RetrieveMultiResponseTest extends BaseTestClass {

    @Autowired
    private WebClient mWebClient;

    @Test
    public void isMultiplicationTableProduceTwelveTimesAndAfterCollected() {
        Flux<Response> responseFlux = this.mWebClient
                .get()
                .uri("/r-math/table/{value}", 4)
                .retrieve()
                .bodyToFlux(Response.class);

        StepVerifier.create(responseFlux)
                .expectNextCount(12L)
                .verifyComplete();
    }

    @Test
    public void isMultiplicationTableStreamDataAsOperationGoesOn() {
        Flux<Response> responseFlux = this.mWebClient
                .get()
                .uri("/r-math/table/{value}/stream", 4)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseFlux)
                .expectNextCount(12L)
                .verifyComplete();
    }

}
