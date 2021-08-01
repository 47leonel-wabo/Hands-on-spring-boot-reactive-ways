package com.aiwa.reactivedemo.exceptionhandler;

import com.aiwa.reactivedemo.dto.InputValueError;
import com.aiwa.reactivedemo.exception.InputValueException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(InputValueException.class)
    public ResponseEntity<InputValueError> invalidInputException(InputValueException valueException) {
        return ResponseEntity
                .badRequest()
                .body(new InputValueError(
                        valueException.getErrorCode(),
                        valueException.getInputValue(),
                        valueException.getMessage()
                ));
    }

}
