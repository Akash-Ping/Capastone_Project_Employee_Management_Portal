package com.employee.Employee.Management.Portal.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataNotFoundExceptionTest {

    @Test
    public void testConstructor() {
        String errorMessage = "Data not found";
        DataNotFoundException exception = new DataNotFoundException(errorMessage);

        assertEquals(errorMessage, exception.getMessage(), "Constructor did not set the message correctly");
    }

    @Test
    public void testExceptionThrown() {
        String errorMessage = "Data not found";

        DataNotFoundException exception = assertThrows(DataNotFoundException.class, () -> {
            throw new DataNotFoundException(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage(), "Exception message did not match");
    }
}
