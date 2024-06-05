package com.employee.Employee.Management.Portal.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomErrorResponseTest {
    @Test
    public void testConstructor() {
        String errorMessage = "This is a test error message";
        CustomErrorResponse errorResponse = new CustomErrorResponse(errorMessage);

        assertEquals(errorMessage, errorResponse.getMessage(), "Constructor did not set the message correctly");
    }

    @Test
    public void testGetMessage() {
        String errorMessage = "This is a test error message";
        CustomErrorResponse errorResponse = new CustomErrorResponse(errorMessage);

        assertEquals(errorMessage, errorResponse.getMessage(), "getMessage did not return the correct message");
    }

    @Test
    public void testSetMessage() {
        String initialMessage = "Initial message";
        String newMessage = "New message";
        CustomErrorResponse errorResponse = new CustomErrorResponse(initialMessage);

        errorResponse.setMessage(newMessage);
        assertEquals(newMessage, errorResponse.getMessage(), "setMessage did not set the new message correctly");
    }
}
