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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getAssignedSkills() {
        return assignedSkills;
    }

    public void setAssignedSkills(List<String> assignedSkills) {
        this.assignedSkills = assignedSkills;
    }

    public List<String> getProjectName() {
        return ProjectName;
    }

    public void setProjectName(List<String> projectName) {
        ProjectName = projectName;
    }

    public String getManagerName() {
        return ManagerName;
    }

    public void setManagerName(String managerName) {
        ManagerName = managerName;
    }

    public Long getEmpManagerId() {
        return EmpManagerId;
    }

    public void setEmpManagerId(Long empManagerId) {
        EmpManagerId = empManagerId;
    }

    public Long getEmpProjectId() {
        return EmpProjectId;
    }

    public void setEmpProjectId(Long empProjectId) {
        EmpProjectId = empProjectId;
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

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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

    @Override
    public String toString() {
        return "ManagerOutDto{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + ", contactNo='" + contactNo + '\''
                + ", designation='" + designation + '\''
                + ", location='" + location + '\''
                + ", empId='" + empId + '\''
                + ", dob='" + dob + '\''
                + ", doj='" + doj + '\''
                + ", EmpProjectId=" + EmpProjectId
                + ", EmpManagerId=" + EmpManagerId
                + ", ManagerName='" + ManagerName + '\''
                + ", ProjectName=" + ProjectName
                + ", assignedSkills=" + assignedSkills
                + '}';
    }
}
