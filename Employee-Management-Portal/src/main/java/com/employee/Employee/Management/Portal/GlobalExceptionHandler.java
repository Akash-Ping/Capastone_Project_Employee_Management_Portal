package com.employee.Employee.Management.Portal;

import com.employee.Employee.Management.Portal.exception.CustomErrorResponse;
import com.employee.Employee.Management.Portal.exception.DataAlreadyExistsException;
import com.employee.Employee.Management.Portal.exception.DataNotFoundException;
import com.employee.Employee.Management.Portal.exception.WrongInputException;
import org.springframework.http.HttpStatus;
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
    public CustomErrorResponse exceptionHandler(final Exception ex) {
        return new CustomErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleEmptyDataValidation(
            final MethodArgumentNotValidException ex) {
        Map<String, String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            resp.put(fieldName, message);
        });
        return resp;
    }
}
