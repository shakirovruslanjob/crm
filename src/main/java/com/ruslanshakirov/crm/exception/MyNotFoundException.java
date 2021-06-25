package com.ruslanshakirov.crm.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MyNotFoundException extends RuntimeException {
    public MyNotFoundException(String message) {
        super(message);
    }
}
