package com.employee.Employee.Management.Portal.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProjectDto {
private Long id;
private String projectName;
private String description;
private String startDate;
private Long manager;
private String head;
private List<String> teamMembers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<String> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Long getManager() {
        return manager;
    }

    public void setManager(Long manager) {
        this.manager = manager;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "ProjectDto{"
                + "id=" + id
                + ", projectName='" + projectName + '\''
                + ", description='" + description + '\''
                + ", startDate='" + startDate + '\''
                + ", manager=" + manager
                + ", head='" + head + '\''
                + ", teamMembers=" + teamMembers
                + '}';
    }
}
