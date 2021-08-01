package com.aiwa.reactivedemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    private final RequestHandler mRequestHandler;

    @Autowired
    public RouterConfig(RequestHandler requestHandler) {
        mRequestHandler = requestHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> serverResponseRouteFunction() {
        return RouterFunctions.route()
                .GET("/router/square/{value}", mRequestHandler::squareRootHandler)
                .GET("/router/table/{value}", mRequestHandler::tableHandler)
                .build();
    }

}
