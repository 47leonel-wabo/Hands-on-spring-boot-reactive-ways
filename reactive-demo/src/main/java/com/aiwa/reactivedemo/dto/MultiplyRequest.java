package com.aiwa.reactivedemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
public class MultiplyRequest {
    private final Integer a;
    private final Integer b;
}
