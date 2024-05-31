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

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Set<String> getAssignedSkills() {
        return assignedSkills;
    }

    public void setAssignedSkills(Set<String> assignedSkills) {
        this.assignedSkills = assignedSkills;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String designation;
    private String location;
    private Set<String> assignedSkills;
    private String projectName;
    private Long projectId;
    private String managerName;

    @Override
    public String toString() {
        return "UserDto{"
                + "empId='" + empId + '\''
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + ", contactNo='" + contactNo + '\''
                + ", dob='" + dob + '\''
                + ", doj='" + doj + '\''
                + ", designation='" + designation + '\''
                + ", location='" + location + '\''
                + ", assignedSkills=" + assignedSkills
                + ", projectName='" + projectName + '\''
                + ", projectId=" + projectId
                + ", managerName='" + managerName + '\''
                + '}';
    }
}


