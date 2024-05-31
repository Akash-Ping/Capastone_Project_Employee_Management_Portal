package com.employee.Employee.Management.Portal.dto;

import lombok.Data;

@Data
public class RequestResourceManagerProjectDto {

    private long id;

    private String projectName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "RequestResourceManagerProjectDto{"
                + "id=" + id
                + ", projectName='" + projectName + '\''
                + '}';
    }
}

