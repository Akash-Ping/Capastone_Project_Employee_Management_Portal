package com.employee.Employee.Management.Portal.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProjectDto {
private Long id;
private String projectName;
private String description;
private String startDate;
private Long manager;
private String head;
private List<String> teamMembers;

}
