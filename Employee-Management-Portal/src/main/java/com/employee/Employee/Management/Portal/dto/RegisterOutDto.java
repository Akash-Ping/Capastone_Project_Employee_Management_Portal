package com.employee.Employee.Management.Portal.dto;

import com.employee.Employee.Management.Portal.entity.Role;
import com.employee.Employee.Management.Portal.entity.Skills;
import lombok.Data;

import java.util.Set;

@Data
public class RegisterOutDto {
    private Long id;
    private String name;
    private String contactNo;
    private String dob;
    private String doj;
    private String designation;
    private String email;
    private String empId;
    private String location;
//    private String password;
    private Role role;
    private Long EmpProjectId;

    private String ManagerName;

    private String ProjectName;

    private Set<Skills> assignedSkills;

    @Override
    public String toString() {
        return "RegisterOutDto(id=" + id
                + ", name=" + name
                + ", contactNo=" + contactNo
                + ", dob=" + dob
                + ", doj=" + doj
                + ", designation=" + designation
                + ", email=" + email
                + ", empId=" + empId
                + ", location=" + location
                + ", role=" + role
                + ", empProjectId=" + EmpProjectId
                + ", managerName=" + ManagerName
                + ", projectName=" + ProjectName
                + ", assignedSkills=" + assignedSkills
                + ")";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Skills> getAssignedSkills() {
        return assignedSkills;
    }

    public void setAssignedSkills(Set<Skills> assignedSkills) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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
}
