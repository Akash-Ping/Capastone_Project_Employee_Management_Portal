package com.employee.Employee.Management.Portal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongInputException extends Exception {

    public WrongInputException(final String message) {
        super(message);
    }

}