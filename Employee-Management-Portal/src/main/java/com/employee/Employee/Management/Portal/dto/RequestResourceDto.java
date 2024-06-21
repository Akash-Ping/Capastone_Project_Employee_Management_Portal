package com.employee.Employee.Management.Portal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestResourceDto {

    @NotBlank(message = "Employee Id is required")
    private String empId;

    @NotBlank(message = "Email is required")
    private String email;

    @NotNull(message = "Project Id is required")
    private Long projectId;

    @NotBlank(message = "Resource Type is required")
    private String comment;

}
