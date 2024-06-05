package com.employee.Employee.Management.Portal.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProjectOutDto {

    private Long id;

    private String projectName;

    private String manager;

    private String startDate;

    private String description;

    private List<String> teamMembers;
}
