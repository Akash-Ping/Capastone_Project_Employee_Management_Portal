package com.employee.Employee.Management.Portal.dto;

import java.util.Objects;

public class ApiResponseDto {

    private String message;


    public ApiResponseDto() {
    }

    public ApiResponseDto(final String message) {
        this.message = message;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiResponseDto that = (ApiResponseDto) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
         return "ApiResponseDto{"
                 + "message='" + message + '\''
                  + '}';
    }
}
