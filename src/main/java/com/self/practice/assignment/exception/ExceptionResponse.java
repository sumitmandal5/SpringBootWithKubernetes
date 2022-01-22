package com.self.practice.assignment.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@ToString
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
}
