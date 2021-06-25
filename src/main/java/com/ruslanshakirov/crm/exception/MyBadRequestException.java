package com.ruslanshakirov.crm.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MyBadRequestException extends RuntimeException{
    public MyBadRequestException(String message) {
        super(message);
    }
}
