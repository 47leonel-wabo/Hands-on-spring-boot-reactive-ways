package com.aiwa.reactivedemo.config;

import com.aiwa.reactivedemo.dto.CalculatorResponse;
import com.aiwa.reactivedemo.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Service
public class CalculatorHandler {

    private final CalculatorService mCalculatorService;

    @Autowired
    public CalculatorHandler(CalculatorService calculatorService) {
        mCalculatorService = calculatorService;
    }

    public Mono<ServerResponse> additionHandler(final ServerRequest request) {
        return processOperation(
                request,
                (a, b) -> ServerResponse.ok().body(
                        this.mCalculatorService.addition(a, b),
                        CalculatorResponse.class)
        );
        // Another less elegant ways
        // return processOperation(request, (a, b) -> ServerResponse.ok().bodyValue(a + b));
    }

    public Mono<ServerResponse> subtractionHandler(final ServerRequest request) {
        return processOperation(
                request,
                (a, b) -> ServerResponse.ok().body(
                        this.mCalculatorService.subtraction(a, b),
                        CalculatorResponse.class)
        );
    }

    public Mono<ServerResponse> multiplicationHandler(final ServerRequest request) {
        return processOperation(
                request,
                (a, b) -> ServerResponse.ok().body(
                        this.mCalculatorService.multiplication(a, b),
                        CalculatorResponse.class)
        );
    }

    public Mono<ServerResponse> divisionHandler(final ServerRequest request) {
        return processOperation(
                request,
                (a, b) -> b != 0 ? ServerResponse.ok().body(
                        this.mCalculatorService.division(a, b),
                        CalculatorResponse.class) :
                        ServerResponse.badRequest().bodyValue("Zero division not allowed!")
        );
    }

    public Mono<ServerResponse> processOperation(
            final ServerRequest request,
            BiFunction<Double, Double, Mono<ServerResponse>> operation) {
        double a = getPathVariableValue(request, "a");
        double b = getPathVariableValue(request, "b");
        return operation.apply(a, b);
    }

    private String getOP(ServerRequest request) {
        return request.headers().header("OP").get(0);
    }

    private double getPathVariableValue(ServerRequest request, String a2) {
        return Double.parseDouble(request.pathVariable(a2));
    }
}
