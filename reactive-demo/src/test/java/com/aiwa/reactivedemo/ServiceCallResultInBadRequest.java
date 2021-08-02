package com.aiwa.reactivedemo;

import com.aiwa.reactivedemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ServiceCallResultInBadRequest extends BaseTestClass {

    @Autowired
    private WebClient mWebClient;

    @Test
    public void isBadRequestForValueOutOfExpectedRangeTest() {
        Mono<Response> responseMono = this.mWebClient
                .get()
                .uri("/r-math/square/{value}/reactive", 22)
                .retrieve()
                .bodyToMono(Response.class);

        StepVerifier.create(responseMono)
                .verifyError(WebClientResponseException.BadRequest.class);
    }
}
