package com.employee.Employee.Management.Portal.dto;

import com.employee.Employee.Management.Portal.dto.LoginOutDto;
import com.employee.Employee.Management.Portal.entity.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LoginOutDtoTest {

    @Test
    void testEquals() {
        // Test equality when all fields are the same
        LoginOutDto login1 = new LoginOutDto();
        login1.setId(1L);
        login1.setEmail("user@example.com");
        login1.setName("John Doe");
        login1.setRole(Role.EMPLOYEE);
        login1.setMessage("Login successful");

        LoginOutDto login2 = new LoginOutDto();
        login2.setId(1L);
        login2.setEmail("user@example.com");
        login2.setName("John Doe");
        login2.setRole(Role.EMPLOYEE);
        login2.setMessage("Login successful");

        assertEquals(login1, login2);

        // Test equality when only some fields are the same
        LoginOutDto login3 = new LoginOutDto();
        login3.setId(1L);
        login3.setEmail("user@example.com");
        login3.setName("John Doe");
        login3.setRole(Role.EMPLOYEE);
        login3.setMessage("Login successful");

        LoginOutDto login4 = new LoginOutDto();
        login4.setId(1L);
        login4.setEmail("user@example.com");
        login4.setName("Jane Doe");
        login4.setRole(Role.EMPLOYEE);
        login4.setMessage("Login successful");

        assertNotEquals(login3, login4);
    }

    @Test
    void testHashCode() {
        // Test hash code consistency
        LoginOutDto login1 = new LoginOutDto();
        login1.setId(1L);
        login1.setEmail("user@example.com");
        login1.setName("John Doe");
        login1.setRole(Role.EMPLOYEE);
        login1.setMessage("Login successful");

        LoginOutDto login2 = new LoginOutDto();
        login2.setId(1L);
        login2.setEmail("user@example.com");
        login2.setName("John Doe");
        login2.setRole(Role.EMPLOYEE);
        login2.setMessage("Login successful");

        assertEquals(login1.hashCode(), login2.hashCode());

        // Test hash code equality when only some fields are the same
        LoginOutDto login3 = new LoginOutDto();
        login3.setId(1L);
        login3.setEmail("user@example.com");
        login3.setName("John Doe");
        login3.setRole(Role.EMPLOYEE);
        login3.setMessage("Login successful");

        LoginOutDto login4 = new LoginOutDto();
        login4.setId(1L);
        login4.setEmail("user@example.com");
        login4.setName("Jane Doe");
        login4.setRole(Role.EMPLOYEE);
        login4.setMessage("Login successful");

        assertNotEquals(login3.hashCode(), login4.hashCode());
    }

    @Test
    void testToString() {
        LoginOutDto login = new LoginOutDto();
        login.setId(1L);
        login.setEmail("user@example.com");
        login.setName("John Doe");
        login.setRole(Role.EMPLOYEE);
        login.setMessage("Login successful");
        login.setJwt("dummy");
        login.setEmpId("N234");

        String expectedString = "LoginOutDto{id=1, email='user@example.com', name='John Doe', role=EMPLOYEE, jwt='dummy', message='Login successful', empId='N234'}";
        assertEquals(expectedString, login.toString());
    }
}
