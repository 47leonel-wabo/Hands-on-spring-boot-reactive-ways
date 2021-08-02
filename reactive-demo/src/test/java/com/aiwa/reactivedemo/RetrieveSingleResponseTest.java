package com.aiwa.reactivedemo;

import com.aiwa.reactivedemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

public class RetrieveSingleResponseTest extends BaseTestClass {

    @Autowired
    private WebClient mWebClient;

    @Test
    public void isSquareRootWorkBlockingWays() {
        Response response = this.mWebClient
                .get()
                .uri("/r-math/square/{value}", 4)
                .retrieve()
                .bodyToMono(Response.class)
                .block(); // block and wait response

        assertThat(response.getOutPut()).isEqualTo(16);
    }

    @Test
    public void isSquareRootWorkViaStepVerifier() {
        Mono<Response> responseMono = this.mWebClient
                .get()
                .uri("/r-math/square/{value}", 4)
                .retrieve()
                .bodyToMono(Response.class);

        StepVerifier.create(responseMono)
                .expectNextMatches(response -> response.getOutPut() == 16)
                .verifyComplete();
    }
}
