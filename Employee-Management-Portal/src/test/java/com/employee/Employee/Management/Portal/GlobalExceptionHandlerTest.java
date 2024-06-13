package com.employee.Employee.Management.Portal;

import com.employee.Employee.Management.Portal.exception.CustomErrorResponse;
import com.employee.Employee.Management.Portal.exception.DataAlreadyExistsException;
import com.employee.Employee.Management.Portal.exception.DataNotFoundException;
import com.employee.Employee.Management.Portal.exception.WrongInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    public void testDataNotFoundExceptionHandler() {
        DataNotFoundException ex = new DataNotFoundException("Data not found");
        CustomErrorResponse response = globalExceptionHandler.dataNotFoundExceptionHandler(ex);

        assertEquals("Data not found", response.getMessage());
    }

    @Test
    public void testDataAlreadyExistsExceptionHandler() {
        DataAlreadyExistsException ex = new DataAlreadyExistsException("Data already exists");
        CustomErrorResponse response = globalExceptionHandler.dataAlreadyExistExceptionHandler(ex);

        assertEquals("Data already exists", response.getMessage());
    }

    @Test
    public void testWrongInputExceptionHandler() {
        WrongInputException ex = new WrongInputException("Wrong input");
        CustomErrorResponse response = globalExceptionHandler.wrongInputExceptionHandler(ex);

        assertEquals("Wrong input", response.getMessage());
    }

    @Test
    public void testExceptionHandler() {
        Exception ex = new Exception("Generic error");
        ResponseEntity<String> response = globalExceptionHandler.handleGenericException(ex);

        assertEquals("An unexpected error occurred: Generic error", response.getBody());
        assertEquals(500, response.getStatusCodeValue());
    }

    @Test
    public void testHandleValidationExceptions() {
        FieldError fieldError = new FieldError("objectName", "fieldName", "defaultMessage");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getAllErrors()).thenReturn(Collections.singletonList(fieldError));

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);
        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleValidationExceptions(ex);

        Map<String, String> expected = new HashMap<>();
        expected.put("fieldName", "defaultMessage");

        assertEquals(expected, response.getBody());
        assertEquals(400, response.getStatusCodeValue());
    }
}
