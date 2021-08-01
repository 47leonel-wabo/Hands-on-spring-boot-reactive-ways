package com.aiwa.reactivedemo.config;

import com.aiwa.reactivedemo.dto.MultiplyRequest;
import com.aiwa.reactivedemo.dto.Response;
import com.aiwa.reactivedemo.exception.InputValueException;
import com.aiwa.reactivedemo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    // Making this functional endpoint stream reactive
    public Mono<ServerResponse> tableStreamHandler(ServerRequest request) {
        int i = Integer.parseInt(request.pathVariable("value"));
        Flux<Response> responseFlux = this.mMathService.table(i);
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(responseFlux, Response.class);
    }

    public Mono<ServerResponse> multiplyHandler(ServerRequest request) {
        Mono<MultiplyRequest> requestMono = request.bodyToMono(MultiplyRequest.class);
        Mono<Response> multiplication = this.mMathService.multiplicationOf(requestMono);
        return ServerResponse.ok().body(multiplication, Response.class);
    }

    // Throw custom exception if condition not met
    public Mono<ServerResponse> squareRootWithValidationHandler(ServerRequest request) {
        int i = Integer.parseInt(request.pathVariable("value"));
        if (i < 10 || i > 20) {
            return Mono.error(new InputValueException((i))); // Emit error signal
        }
        Mono<Response> responseMono = this.mMathService.square(i);
        return ServerResponse.ok().body(responseMono, Response.class);
    }
}
