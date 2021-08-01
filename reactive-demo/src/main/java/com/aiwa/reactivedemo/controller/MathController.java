package com.aiwa.reactivedemo.controller;

import com.aiwa.reactivedemo.dto.Response;
import com.aiwa.reactivedemo.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = {"/math"})
public class MathController {

    private final MathService mMathService;

    @Autowired
    public MathController(MathService mathService) {
        mMathService = mathService;
    }

    @GetMapping(path = {"/square/{value}"})
    public Response getSquareRoot(final @PathVariable("value") Integer value) {
        return this.mMathService.calculatesquareRoot(value);
    }

    @GetMapping(path = {"/table/{value}"})
    public List<Response> getMultiplicationTable(@PathVariable("value") final Integer value){
        return this.mMathService.multiplicationTable(value);
    }
}
