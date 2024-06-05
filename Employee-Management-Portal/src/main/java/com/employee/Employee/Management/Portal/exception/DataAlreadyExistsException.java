package com.employee.Employee.Management.Portal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DataAlreadyExistsException extends RuntimeException {

//    private static final long serialVersionUID = 1L;

    public DataAlreadyExistsException(final String message) {
        super(message);
    }
}