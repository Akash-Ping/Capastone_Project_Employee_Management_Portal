package com.employee.Employee.Management.Portal.dto;

import lombok.Data;

@Data
public class ResourceRequestsAdminOutDto {

    private Long id;

    private Long employeeId;

    private String employeeName;

    private String managerName;

    private String projectName;

    private String comment;

    private Long managerId;

    private Long projectId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "ResourceRequestsAdminOutDto{"
                + "id=" + id
                + ", employeeId=" + employeeId
                + ", employeeName='" + employeeName + '\''
                + ", managerName='" + managerName + '\''
                + ", projectName='" + projectName + '\''
                + ", comment='" + comment + '\''
                + ", managerId=" + managerId
                + ", projectId=" + projectId
                + '}';
    }
}
