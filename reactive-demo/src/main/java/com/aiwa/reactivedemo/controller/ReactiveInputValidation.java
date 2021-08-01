package com.aiwa.reactivedemo.controller;

import com.aiwa.reactivedemo.dto.Response;
import com.aiwa.reactivedemo.exception.InputValueException;
import com.aiwa.reactivedemo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = {"/r-math"})
public class ReactiveInputValidation {

    private final ReactiveMathService mReactiveMathService;

    @Autowired
    public ReactiveInputValidation(ReactiveMathService reactiveMathService) {
        mReactiveMathService = reactiveMathService;
    }

    @GetMapping(path = {"/square/{value}/thrown"})
    public Mono<Response> squareRoot(final @PathVariable("value") Integer value) {
        if (value < 10 || value > 20)
            throw new InputValueException(value);
        return this.mReactiveMathService.square(value);
    }
}
