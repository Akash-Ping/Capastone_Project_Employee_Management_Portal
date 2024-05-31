package com.employee.Employee.Management.Portal.dto;

import com.employee.Employee.Management.Portal.entity.Role;
import lombok.Data;

import java.util.Set;


@Data
public class EmpDto {

    private Long id;
    private String name;
    private String contactNo;
    private String dob;
    private String doj;
    private String designation;
    private String email;
    private String empId;
    private String location;
    private String password;
    private Role role;
    private Long EmpManagerId;
    private Long EmpProjectId;
    private String ManagerName;
    private String ProjectName;
    private Set<String> assignedSkills;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<String> getAssignedSkills() {
        return assignedSkills;
    }

    public void setAssignedSkills(Set<String> assignedSkills) {
        this.assignedSkills = assignedSkills;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getManagerName() {
        return ManagerName;
    }

    public void setManagerName(String managerName) {
        ManagerName = managerName;
    }

    public Long getEmpProjectId() {
        return EmpProjectId;
    }

    public void setEmpProjectId(Long empProjectId) {
        EmpProjectId = empProjectId;
    }

    public Long getEmpManagerId() {
        return EmpManagerId;
    }

    public void setEmpManagerId(Long empManagerId) {
        EmpManagerId = empManagerId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EmpDto{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", contactNo='" + contactNo + '\''
                + ", dob='" + dob + '\''
                + ", doj='" + doj + '\''
                + ", designation='" + designation + '\''
                + ", email='" + email + '\''
                + ", empId='" + empId + '\''
                + ", location='" + location + '\''
                + ", password='" + password + '\''
                + ", role=" + role
                + ", EmpManagerId=" + EmpManagerId
                + ", EmpProjectId=" + EmpProjectId
                + ", ManagerName='" + ManagerName + '\''
                + ", ProjectName='" + ProjectName + '\''
                + ", assignedSkills=" + assignedSkills
                + '}';
    }
}


