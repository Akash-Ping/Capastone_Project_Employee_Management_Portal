package com.employee.Employee.Management.Portal.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WrongInputExceptionTest {

    @Test
    public void testConstructor() {
        String errorMessage = "Wrong input provided";
        WrongInputException exception = new WrongInputException(errorMessage);

        assertEquals(errorMessage, exception.getMessage(), "Constructor did not set the message correctly");
    }

    @Test
    public void testExceptionThrown() {
        String errorMessage = "Wrong input provided";

        WrongInputException exception = assertThrows(WrongInputException.class, () -> {
            throw new WrongInputException(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage(), "Exception message did not match");
    }
}
