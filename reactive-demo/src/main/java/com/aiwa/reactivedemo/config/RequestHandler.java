package com.aiwa.reactivedemo.config;

import com.aiwa.reactivedemo.dto.Response;
import com.aiwa.reactivedemo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RequestHandler {

    private final ReactiveMathService mMathService;

    @Autowired
    public RequestHandler(ReactiveMathService mathService) {
        mMathService = mathService;
    }

    public Mono<ServerResponse> squareRootHandler(ServerRequest request) {
        int i = Integer.parseInt(request.pathVariable("value"));
        Mono<Response> responseMono = this.mMathService.square(i);
        return ServerResponse.ok().body(responseMono, Response.class);
        // use bodyValue(), instead of body() for non publisher types
    }

    public Mono<ServerResponse> tableHandler(ServerRequest request) {
        int i = Integer.parseInt(request.pathVariable("value"));
        Flux<Response> responseFlux = this.mMathService.table(i);
        return ServerResponse.ok().body(responseFlux, Response.class);
    }
}
