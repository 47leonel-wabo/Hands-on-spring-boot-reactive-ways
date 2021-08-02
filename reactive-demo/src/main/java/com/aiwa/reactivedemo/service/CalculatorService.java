package com.aiwa.reactivedemo.service;

import com.aiwa.reactivedemo.dto.CalculatorResponse;
import com.aiwa.reactivedemo.exception.CalculatorZeroDivisionException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CalculatorService {

    public Mono<CalculatorResponse> addition(final Double a, final Double b){
        return Mono
                .fromSupplier(() -> a + b)
                .map(result -> new CalculatorResponse(String.format("%s + %s ", a, b), result));
    }

    public Mono<CalculatorResponse> multiplication(final Double a, final Double b){
        return Mono
                .fromSupplier(() -> a * b)
                .map(result -> new CalculatorResponse(String.format("%s * %s ", a, b), result));
    }

    public Mono<CalculatorResponse> subtraction(final Double a, final Double b){
        return Mono
                .fromSupplier(() -> a - b)
                .map(result -> new CalculatorResponse(String.format("%s - %s ", a, b), result));
    }

    public Mono<CalculatorResponse> division(final Double a, final Double b){
        return Mono
                .fromSupplier(() -> a / b)
                .map(result -> new CalculatorResponse(String.format("%s / %s ", a, b), result));
    }
}
