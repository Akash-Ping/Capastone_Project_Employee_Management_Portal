package com.employee.Employee.Management.Portal.dto;

import lombok.Data;

@Data
public class ResourceRequestsAdminOutDto {

    private Long id;

    private Long employeeId;

    private String employeeName;

    private String managerName;

    private String projectName;

    private String comment;

    private Long managerId;

    private Long projectId;


}
