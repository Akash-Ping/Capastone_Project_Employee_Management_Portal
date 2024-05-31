package com.employee.Employee.Management.Portal.dto;

import lombok.Data;

@Data
public class RequestResourceDto {

    private String empId;

    private String email;

    private Long projectId;

    private String comment;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "RequestResourceDto{"
                + "empId='" + empId + '\''
                + ", email='" + email + '\''
                + ", projectId=" + projectId
                + ", comment='" + comment + '\''
                + '}';
    }
}

