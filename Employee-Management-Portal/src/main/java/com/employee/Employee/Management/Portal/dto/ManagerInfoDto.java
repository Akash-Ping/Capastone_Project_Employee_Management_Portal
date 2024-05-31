package com.employee.Employee.Management.Portal.dto;

import lombok.Data;

@Data
public class ManagerInfoDto {

    private Long id;
    private String managerName;
    private String managerEmployeeId;

    public ManagerInfoDto() {

    }

    @Override
    public String toString() {
        return "ManagerInfoDto{"
                + "id=" + id
                + ", managerName='" + managerName + '\''
                + ", managerEmployeeId='" + managerEmployeeId + '\''
                + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManagerEmployeeId() {
        return managerEmployeeId;
    }

    public void setManagerEmployeeId(String managerEmployeeId) {
        this.managerEmployeeId = managerEmployeeId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}
