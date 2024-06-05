package com.employee.Employee.Management.Portal;

import com.employee.Employee.Management.Portal.exception.CustomErrorResponse;
import com.employee.Employee.Management.Portal.exception.DataAlreadyExistsException;
import com.employee.Employee.Management.Portal.exception.DataNotFoundException;
import com.employee.Employee.Management.Portal.exception.WrongInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        CustomErrorResponse response = globalExceptionHandler.exceptionHandler(ex);

        assertEquals("Generic error", response.getMessage());
    }

    @Test
    public void testHandleEmptyDataValidation() {
        FieldError fieldError = new FieldError("objectName", "fieldName", "defaultMessage");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getAllErrors()).thenReturn(Collections.singletonList(fieldError));

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);
        Map<String, String> response = globalExceptionHandler.handleEmptyDataValidation(ex);

        Map<String, String> expected = new HashMap<>();
        expected.put("fieldName", "defaultMessage");

        assertEquals(expected, response);
    }
}
