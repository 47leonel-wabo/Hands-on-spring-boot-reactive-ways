package com.aiwa.reactivedemo.exception;

public class InputValueException extends RuntimeException {
    private static final Integer errorCode = 747;
    private static final String MSG = "Range not allowed, choose between 10 - 20";
    private final Integer inputValue;

    public InputValueException(Integer inputValue) {
        super(MSG);
        this.inputValue = inputValue;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public Integer getInputValue() {
        return inputValue;
    }
}
