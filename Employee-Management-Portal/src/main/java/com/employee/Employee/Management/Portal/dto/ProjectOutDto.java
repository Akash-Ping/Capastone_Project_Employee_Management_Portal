package com.employee.Employee.Management.Portal.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProjectOutDto {

    private Long id;

    private String projectName;

    private String manager;

    private String startDate;

    private String description;

    private List<String> teamMembers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<String> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<String> teamMembers) {
        this.teamMembers = teamMembers;
    }

    @Override
    public String toString() {
        return "ProjectOutDto{"
                + "id=" + id
                + ", projectName='" + projectName + '\''
                + ", manager='" + manager + '\''
                + ", startDate='" + startDate + '\''
                + ", description='" + description + '\''
                + ", teamMembers=" + teamMembers
                + '}';
    }
}
