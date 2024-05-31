package com.employee.Employee.Management.Portal.dto;

import lombok.Data;

@Data
public class AssignProjectDto {

    private Long id;
    private String empId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "AssignProjectDto{"
                + "id=" + id
                + ", empId='" + empId + '\''
                + '}';
    }
}

