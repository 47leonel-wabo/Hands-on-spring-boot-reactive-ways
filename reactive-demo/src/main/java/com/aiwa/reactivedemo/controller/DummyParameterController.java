package com.aiwa.reactivedemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(path = {"/jobs"})
public class DummyParameterController {

    @GetMapping(path = {"/search"})
    public Flux<Integer> validateParams(
            final @RequestParam("count") Integer count,
            final @RequestParam("page") Integer page
    ){
        return Flux.just(count, page);
    }

}
