package com.employee.Employee.Management.Portal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class ProjectDto {
private Long id;

@NotBlank(message = "Project Name is required")
private String projectName;

@NotBlank(message = "Description is required")
private String description;

@NotBlank(message = "Date is required")
private String startDate;
private Long manager;
private String head;
private List<String> teamMembers;

}
