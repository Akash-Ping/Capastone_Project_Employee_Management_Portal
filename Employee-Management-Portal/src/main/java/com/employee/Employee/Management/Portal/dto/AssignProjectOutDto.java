package com.employee.Employee.Management.Portal.dto;

import lombok.Data;

@Data
public class AssignProjectOutDto {
    private Long id;
    private String projectName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return "AssignProjectOutDto{"
                + "id=" + id
                + ", projectName='" + projectName + '\''
                + '}';
    }
}
