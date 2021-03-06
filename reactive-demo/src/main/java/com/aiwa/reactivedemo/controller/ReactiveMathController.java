package com.aiwa.reactivedemo.controller;

import com.aiwa.reactivedemo.dto.MultiplyRequest;
import com.aiwa.reactivedemo.dto.Response;
import com.aiwa.reactivedemo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping(path = {"/r-math"})
public class ReactiveMathController {

    private final ReactiveMathService mReactiveMathService;

    @Autowired
    public ReactiveMathController(ReactiveMathService reactiveMathService) {
        mReactiveMathService = reactiveMathService;
    }

    @GetMapping(path = {"/square/{value}"})
    public Mono<Response> getSquareRoot(final @PathVariable("value") Integer value) {
        return this.mReactiveMathService.square(value);
    }

    // Here, data are proceeded completely before sent back
    @GetMapping(path = {"/table/{value}"})
    public Flux<Response> getMultiplicationTable(final @PathVariable("value") Integer value) {
        return this.mReactiveMathService.table(value);
    }

    // Here, data are sent as proceeded
    @GetMapping(path = {"/table/{value}/stream"}, produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<Response> getMultiplicationTableStream(final @PathVariable("value") Integer value) {
        return this.mReactiveMathService.table(value);
    }

    @PostMapping(path = {"/multiply"})
    public Mono<Response> multiply(
            final @RequestBody Mono<MultiplyRequest> multiplyRequestMono,
            final @RequestHeader Map<String, String> headers
    ) {
        System.out.println(headers);
        return this.mReactiveMathService.multiplicationOf(multiplyRequestMono);
    }
}
