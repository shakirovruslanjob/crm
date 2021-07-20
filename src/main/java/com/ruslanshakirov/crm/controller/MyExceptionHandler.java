package com.ruslanshakirov.crm.controller;

import com.ruslanshakirov.crm.dto.MessageResponse;
import com.ruslanshakirov.crm.exception.MyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MyNotFoundException.class)
    public ResponseEntity<MessageResponse> handleMyNotFound(MyNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(e.getMessage()));
    }
}
