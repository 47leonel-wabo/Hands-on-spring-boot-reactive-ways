package com.aiwa.reactivedemo.exception;

public class CalculatorZeroDivisionException extends RuntimeException {
    private static final String MSG = "Zero division not allowed!";

    public CalculatorZeroDivisionException() {
        super(MSG);
    }
}
