package com.aiwa.rmd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = {"/coin/price/v1"})
public class PriceController {

    @GetMapping(path = {"/{name}"})
    public Mono<String> getPrice(final @PathVariable("name") String name) {
        return Mono.fromSupplier(() -> "price");
    }

}
