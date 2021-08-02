package com.aiwa.reactivedemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient() {
        return WebClient
                .builder()
                .baseUrl("http://localhost:8080")
//                .defaultHeaders(httpHeaders -> httpHeaders.setBasicAuth("username", "password"))
                .filter(this::sessionToken) // for any subsequence requests
                .build();
    }

//    private Mono<ClientResponse> sessionToken(
//            ClientRequest request, ExchangeFunction ef
//    ) {
//        System.out.println("Creating a session token");
//        // Generate or validate JWT
//        ClientRequest clientRequest = ClientRequest
//                .from(request)
//                .headers(httpHeaders -> httpHeaders.setBearerAuth("jwt-token-goes-here"))
//                .build();
//
//        return ef.exchange(clientRequest);
//    }

    private Mono<ClientResponse> sessionToken(
            ClientRequest request, ExchangeFunction ef
    ) {
        // attribute name 'auth' --> basic or oauth
        ClientRequest clientRequest = request.attribute("auth")
                .map(o -> o.equals("basic") ? withBasicAuth(request) : withOAuth(request))
                .orElse(request);

        return ef.exchange(clientRequest);
    }

    private ClientRequest withBasicAuth(ClientRequest request) {
        return ClientRequest.from(request)
                .headers(httpHeaders -> httpHeaders.setBasicAuth("user", "pass"))
                .build();
    }

    private ClientRequest withOAuth(ClientRequest request) {
        return ClientRequest.from(request)
                .headers(httpHeaders -> httpHeaders.setBearerAuth("OAuth-token-here"))
                .build();
    }
}
