package com.aiwa.reactivedemo;

import com.aiwa.reactivedemo.dto.CalculatorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
public class CalCalculatorTests {

    @Autowired
    private WebClient mWebClient;

    @Test
    public void isAdditionOperationReturnSingleValueTest(){

        Mono<CalculatorResponse> responseMono = this.mWebClient
                .get()
                .uri("/{a}/{b}", 2, 3)
                .headers(httpHeaders -> httpHeaders.set("OP", "+"))
                .retrieve()
                .bodyToMono(CalculatorResponse.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseMono)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void isAdditionOperationReturnCorrectValueTest(){

        Mono<CalculatorResponse> responseMono = this.mWebClient
                .get()
                .uri("/{a}/{b}", 2, 3)
                .headers(httpHeaders -> httpHeaders.set("OP", "+"))
                .retrieve()
                .bodyToMono(CalculatorResponse.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseMono)
                .expectNextMatches(calculatorResponse -> calculatorResponse.getResult() == 5.0)
                .verifyComplete();
    }
}
