package com.employee.Employee.Management.Portal.exception;

public class InvalidEmailDomainException extends RuntimeException {
    public InvalidEmailDomainException(String message) {
        super(message);
    }
}