package com.ebanx.ebank.adapter.exception;

import com.ebanx.ebank.usecase.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class EbankExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Long> notFoundException(NotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(0L, HttpStatus.NOT_FOUND);
    }
}
