package com.employee.Employee.Management.Portal;

import com.employee.Employee.Management.Portal.exception.WrongInputException;
import com.employee.Employee.Management.Portal.exception.InvalidEmailDomainException;
import com.employee.Employee.Management.Portal.exception.DataAlreadyExistsException;
import com.employee.Employee.Management.Portal.exception.DataNotFoundException;
import com.employee.Employee.Management.Portal.exception.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public CustomErrorResponse dataNotFoundExceptionHandler(
            final DataNotFoundException ex) {
        return new CustomErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(DataAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public CustomErrorResponse dataAlreadyExistExceptionHandler(
            final DataAlreadyExistsException ex) {
        return new CustomErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(InvalidEmailDomainException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomErrorResponse invalidEmailDomainExceptionHandler(final InvalidEmailDomainException ex) {
        return new CustomErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(WrongInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomErrorResponse wrongInputExceptionHandler(
            final WrongInputException ex) {
        return new CustomErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
