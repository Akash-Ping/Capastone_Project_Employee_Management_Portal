package com.employee.Employee.Management.Portal.dto;

import lombok.Data;

@Data
public class RequestedDto {

    private String empId;

    private String email;

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

    @Override
    public String toString() {
        return "RequestedDto{"
                + "empId='" + empId + '\''
                + ", email='" + email + '\''
                + '}';
    }
}
