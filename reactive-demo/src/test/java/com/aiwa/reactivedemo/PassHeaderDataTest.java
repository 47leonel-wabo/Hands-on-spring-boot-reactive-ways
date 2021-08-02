package com.aiwa.reactivedemo;

import com.aiwa.reactivedemo.dto.MultiplyRequest;
import com.aiwa.reactivedemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class PassHeaderDataTest extends BaseTestClass {

    @Autowired
    private WebClient mWebClient;

    @Test
    public void isHeaderDataCorrectlyReceivedByService() {
        Mono<Response> responseMono = this.mWebClient
                .post()
                .uri("/r-math/multiply")
                .bodyValue(new MultiplyRequest(7, 2))
                .headers(httpHeaders -> httpHeaders.set("OP-key", "header-value")) // pass header key - value
                .retrieve()
                .bodyToMono(Response.class);

        StepVerifier.create(responseMono)
                .expectNextCount(1)
                .verifyComplete();
    }

}
