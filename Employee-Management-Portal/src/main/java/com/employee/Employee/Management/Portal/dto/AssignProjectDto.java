package com.employee.Employee.Management.Portal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssignProjectDto {

    @NotNull(message = "Project Id is required")
    private Long id;

    @NotBlank(message = "Employee Id is required")
    private String empId;
}
