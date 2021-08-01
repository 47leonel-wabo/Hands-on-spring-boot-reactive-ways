package com.aiwa.reactivedemo.service;

import com.aiwa.reactivedemo.dto.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MathService {

    public Response calculatesquareRoot(final Integer v) {
        return new Response(v * v);
    }

    public List<Response> multiplicationTable(final Integer v) {
        return IntStream
                .rangeClosed(1, 12)
                .peek(i -> Sleep.sleepSecond())
                .peek(i -> System.out.printf("processing element %s%n", i))
                .mapToObj(i -> new Response(i * v))
                .collect(Collectors.toList());
    }

}
