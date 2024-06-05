package com.employee.Employee.Management.Portal.dto;

import lombok.Data;

@Data
public class RequestResourceDto {

    private String empId;

    private String email;

    private Long projectId;

    private String comment;

}
