package com.employee.Employee.Management.Portal.dto;

import lombok.Data;

import java.util.List;

@Data
public class ManagerOutDto {

    private Long id;
    private String name;
    private String email;
    private String contactNo;
    private String designation;
    private String location;
    private String empId;
    private String dob;
    private String doj;
    private Long EmpProjectId;
    private Long EmpManagerId;
    private String ManagerName;
    private List<String> ProjectName;
    private List<String> assignedSkills;

    public ManagerOutDto() {

    }
}
