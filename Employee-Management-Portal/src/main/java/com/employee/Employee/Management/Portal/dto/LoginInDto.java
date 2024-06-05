package com.employee.Employee.Management.Portal.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class LoginInDto {
    private String email;
    private String password;

    public LoginInDto() {
    }

    public LoginInDto(final String email, final String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginInDto loginDto = (LoginInDto) o;
        return Objects.equals(email, loginDto.email) && Objects.equals(password, loginDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "LoginDto{"
                + "email='" + email + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
