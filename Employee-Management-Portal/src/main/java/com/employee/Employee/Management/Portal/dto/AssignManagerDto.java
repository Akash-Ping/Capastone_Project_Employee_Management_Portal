package com.employee.Employee.Management.Portal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssignManagerDto {

    @NotNull(message = "Project Id is required")
    private Long projectId;

    @NotBlank(message = "Manager Id is required")
    private String managerId;
}