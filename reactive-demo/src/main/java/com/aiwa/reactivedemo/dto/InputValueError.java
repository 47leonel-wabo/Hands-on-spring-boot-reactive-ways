package com.aiwa.reactivedemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class InputValueError {
    private final Integer errorCode;
    private final Integer inputValue;
    private final String message;
}
