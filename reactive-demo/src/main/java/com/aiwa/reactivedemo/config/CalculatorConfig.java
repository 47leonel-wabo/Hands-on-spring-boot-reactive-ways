package com.aiwa.reactivedemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class CalculatorConfig {

    private final CalculatorHandler mCalculatorHandler;

    @Autowired
    public CalculatorConfig(CalculatorHandler calculatorHandler) {
        mCalculatorHandler = calculatorHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> calculatorHighRoute() {
        return RouterFunctions.route()
                .path("/calculator", this::serveCalculatorRoutes)
                .build();
    }

    private RouterFunction<ServerResponse> serveCalculatorRoutes() {
        return RouterFunctions.route()
                .GET("/{a}/{b}", this.checkOperation("+"), mCalculatorHandler::additionHandler)
                .GET("/{a}/{b}", this.checkOperation("-"), mCalculatorHandler::subtractionHandler)
                .GET("/{a}/{b}", this.checkOperation("*"), mCalculatorHandler::multiplicationHandler)
                .GET("/{a}/{b}", this.checkOperation("/"), mCalculatorHandler::divisionHandler)
                .GET("/{a}/{b}", serverRequest -> ServerResponse.badRequest().bodyValue("Operation not supported"))
                .build();
    }

    private RequestPredicate checkOperation(final String operationKey) {
        return RequestPredicates
                .headers(headers -> headers
                        .asHttpHeaders()
                        .toSingleValueMap()
                        .get("OP")
                        .equals(operationKey)
                );
    }
}
