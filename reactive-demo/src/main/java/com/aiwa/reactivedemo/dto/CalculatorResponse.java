package com.aiwa.reactivedemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
public class CalculatorResponse {
    private final String operation;
    private final Double result;
}
