package com.employee.Employee.Management.Portal.dto;

import com.employee.Employee.Management.Portal.entity.Role;

import java.util.Objects;

public class LoginOutDto {

    private Long id;
    private String email;
    private String name;
    private Role role;
    private String jwt;
    private String message;
    private String empId;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginOutDto that = (LoginOutDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(email, that.email)
                && Objects.equals(name, that.name)
                && role == that.role
                && Objects.equals(jwt, that.jwt)
                && Objects.equals(message, that.message)
                && Objects.equals(empId, that.empId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(email);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(role);
        result = 31 * result + Objects.hashCode(jwt);
        result = 31 * result + Objects.hashCode(message);
        result = 31 * result + Objects.hashCode(empId);
        return result;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }



    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LoginOutDto{"
                + "id=" + id
                + ", email='" + email + '\''
                + ", name='" + name + '\''
                + ", role=" + role
                + ", jwt='" + jwt + '\''
                + ", message='" + message + '\''
                + ", empId='" + empId + '\''
                + '}';
    }
}
