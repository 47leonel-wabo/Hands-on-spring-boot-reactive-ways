package com.aiwa.reactivedemo;

import com.aiwa.reactivedemo.dto.MultiplyRequest;
import com.aiwa.reactivedemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class PassDataViaPostTest extends BaseTestClass {

    @Autowired
    private WebClient mWebClient;

    @Test
    public void isDataCorrectlyPassToService() {
        Mono<Response> responseMono = this.mWebClient
                .post()
                .uri("/r-math/multiply")
                .bodyValue(new MultiplyRequest(7, 3))
                .retrieve()
                .bodyToMono(Response.class);

        StepVerifier.create(responseMono)
                .expectNextMatches(response -> response.getOutPut() == 21)
                .verifyComplete();
    }

    @Test
    public void isDataCorrectlyPassToServiceAndOnlyOneResultBack() {
        Mono<Response> responseMono = this.mWebClient
                .post()
                .uri("/r-math/multiply")
                .bodyValue(new MultiplyRequest(7, 3))
                .retrieve()
                .bodyToMono(Response.class);

        StepVerifier.create(responseMono)
                .expectNextCount(1L)
                .verifyComplete();
    }
}
