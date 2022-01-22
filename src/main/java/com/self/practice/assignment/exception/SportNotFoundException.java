package com.self.practice.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SportNotFoundException extends RuntimeException {
    public SportNotFoundException(String message) {
        super(message);
    }
}
