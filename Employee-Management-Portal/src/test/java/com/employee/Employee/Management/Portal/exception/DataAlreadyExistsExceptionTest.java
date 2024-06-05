package com.employee.Employee.Management.Portal.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataAlreadyExistsExceptionTest {

    @Test
    public void testConstructor() {
        String errorMessage = "Data already exists";
        DataAlreadyExistsException exception = new DataAlreadyExistsException(errorMessage);

        assertEquals(errorMessage, exception.getMessage(), "Constructor did not set the message correctly");
    }

    @Test
    public void testExceptionThrown() {
        String errorMessage = "Data already exists";

        DataAlreadyExistsException exception = assertThrows(DataAlreadyExistsException.class, () -> {
            throw new DataAlreadyExistsException(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage(), "Exception message did not match");
    }
}
