package com.self.practice.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SubjectStreamNotFoundException extends RuntimeException {
    public SubjectStreamNotFoundException(String message) {
        super(message);
    }
}
