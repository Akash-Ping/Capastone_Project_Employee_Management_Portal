package com.employee.Employee.Management.Portal.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private String empId;
    private String name;
    private String email;
    private String contactNo;
    private String dob;
    private String doj;
    private String designation;
    private String location;
    private Set<String> assignedSkills;
    private String projectName;
    private Long projectId;
    private String managerName;
}
