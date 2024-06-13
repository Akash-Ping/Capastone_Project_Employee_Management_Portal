package com.employee.Employee.Management.Portal.dto;

import com.employee.Employee.Management.Portal.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.Set;

public class RegisterDto {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Contact number is required")
    private String contactNo;

    @NotBlank(message = "Date of birth is required")
    private String dob;

    @NotBlank(message = "Date of joining is required")
    private String doj;

    @NotBlank(message = "Designation is required")
    private String designation;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Employee ID is required")
    private String empId;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "Role is required")
    private Role role;

    //    @NotNull(message = "Manager ID is required")
    private Long EmpManagerId;

    //    @NotNull(message = "Project ID is required")
    private Long EmpProjectId;

    private String ManagerName;

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(final String projectName) {
        ProjectName = projectName;
    }

    private String ProjectName;

    @NotNull(message = "Assigned skills are required")
    private Set<Long> assignedSkills;


    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(final String contactNo) {
        this.contactNo = contactNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(final String dob) {
        this.dob = dob;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(final String doj) {
        this.doj = doj;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(final String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(final String empId) {
        this.empId = empId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    public Long getEmpManagerId() {
        return EmpManagerId;
    }

    public void setEmpManagerId(final Long empManagerId) {
        EmpManagerId = empManagerId;
    }

    public Long getEmpProjectId() {
        return EmpProjectId;
    }

    public void setEmpProjectId(final Long empProjectId) {
        EmpProjectId = empProjectId;
    }

    public Set<Long> getAssignedSkills() {
        return assignedSkills;
    }

    public void setAssignedSkills(final Set<Long> assignedSkills) {
        this.assignedSkills = assignedSkills;
    }

    public String getManagerName() {
        return ManagerName;
    }

    public void setManagerName(final String ManagerName) {
        this.ManagerName = ManagerName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterDto that = (RegisterDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(contactNo, that.contactNo)
                && Objects.equals(dob, that.dob)
                && Objects.equals(doj, that.doj)
                && Objects.equals(designation, that.designation)
                && Objects.equals(email, that.email)
                && Objects.equals(empId, that.empId)
                && Objects.equals(location, that.location)
                && Objects.equals(password, that.password)
                && role == that.role
                && Objects.equals(EmpManagerId, that.EmpManagerId)
                && Objects.equals(EmpProjectId, that.EmpProjectId)
                && Objects.equals(ManagerName, that.ManagerName)
                && Objects.equals(ProjectName, that.ProjectName)
                && Objects.equals(assignedSkills, that.assignedSkills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contactNo, dob, doj, designation,
                email, empId, location, password, role, EmpManagerId, EmpProjectId,
                ManagerName, ProjectName, assignedSkills);
    }

    @Override
    public String toString() {
        return "RegisterDto{"
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
