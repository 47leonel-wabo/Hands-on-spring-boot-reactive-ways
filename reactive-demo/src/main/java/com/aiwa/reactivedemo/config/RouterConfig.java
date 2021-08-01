package com.aiwa.reactivedemo.config;

import com.aiwa.reactivedemo.dto.InputValueError;
import com.aiwa.reactivedemo.exception.InputValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Configuration
public class RouterConfig {

    private final RequestHandler mRequestHandler;

    @Autowired
    public RouterConfig(RequestHandler requestHandler) {
        mRequestHandler = requestHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> highLevelRouter() {
        return RouterFunctions.route()
                .path("/router", this::serverResponseRouteFunction)
                .build();
    }

    /*
     * POSSIBLE TO HAVE MULTIPLE ROUTE HANDLER FUNCTION BEAN
     */
//    @Bean
    private RouterFunction<ServerResponse> serverResponseRouteFunction() {
        return RouterFunctions.route()
                .GET(
                        "/square/{value}",
                        RequestPredicates.path("*/1?") //multiple matches
                                .or(RequestPredicates.path("*/20")),
                        mRequestHandler::squareRootHandler
                ) // if match
                .GET("/square/{value}", serverRequest -> ServerResponse.badRequest().bodyValue("Only values between 10 - 20")) // if no match
                .GET("/table/{value}", mRequestHandler::tableHandler)
                .GET("/table/{value}/stream", mRequestHandler::tableStreamHandler)
                .POST("/multiply", mRequestHandler::multiplyHandler)
                .GET("/square/{value}/validation", mRequestHandler::squareRootWithValidationHandler)
                .onError(InputValueException.class, exceptionHandler())
                .build();
    }

    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler() {
        return (error, request) -> {
            InputValueException ex = (InputValueException) error;
            InputValueError valueError = new InputValueError(ex.getErrorCode(), ex.getInputValue(), ex.getMessage());
            return ServerResponse.badRequest().bodyValue(valueError);
        };
    }
}
