package com.aiwa.reactivedemo;

import com.aiwa.reactivedemo.dto.Response;
import com.aiwa.reactivedemo.exception.InputValueException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ExchangeTest extends BaseTestClass {

    @Autowired
    private WebClient mWebClient;

    // exchange = retrieve + other information (like: http status code, etc.)
    @Test
    public void performExchangeToThrowBadRequestTestOrGetExpectedDataIfNoError() {
        Mono<Object> objectMono = this.mWebClient
                .get()
                .uri("/r-math/square/{value}/reactive", 7)
                .exchangeToMono(clientResponse ->
                        clientResponse.rawStatusCode() == 400 ?
                                clientResponse.bodyToMono(InputValueException.class) :
                                clientResponse.bodyToMono(Response.class))
                .doOnError(throwable -> System.out.println(throwable.getMessage()));

        StepVerifier.create(objectMono)
                .expectNextCount(1)
                .verifyComplete();
    }

}
