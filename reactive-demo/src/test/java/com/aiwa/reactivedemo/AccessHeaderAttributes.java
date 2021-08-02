package com.aiwa.reactivedemo;

import com.aiwa.reactivedemo.dto.MultiplyRequest;
import com.aiwa.reactivedemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class AccessHeaderAttributes extends BaseTestClass{

    @Autowired
    private WebClient mWebClient;

    @Test
    public void selectAuthBasedOnAttributeValueBasic() {
        Mono<Response> responseMono = this.mWebClient
                .post()
                .uri("/r-math/multiply")
                .bodyValue(new MultiplyRequest(7, 2))
                .attribute("auth", "basic")// switch between 'basic' and 'oauth' to change config
                .retrieve()
                .bodyToMono(Response.class);

        StepVerifier.create(responseMono)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void selectAuthBasedOnAttributeValueOAuth() {
        Mono<Response> responseMono = this.mWebClient
                .post()
                .uri("/r-math/multiply")
                .bodyValue(new MultiplyRequest(7, 2))
                .attribute("auth", "oauth")// switch between 'basic' and 'oauth' to change config
                .retrieve()
                .bodyToMono(Response.class);

        StepVerifier.create(responseMono)
                .expectNextCount(1)
                .verifyComplete();
    }
}
