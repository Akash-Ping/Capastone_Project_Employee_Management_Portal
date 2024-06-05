package com.employee.Employee.Management.Portal.exception;

public class CustomErrorResponse {

    private String message;

    public CustomErrorResponse(final String messageLocal) {
        this.message = messageLocal;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String messageLocal) {
        this.message = messageLocal;
    }
}
