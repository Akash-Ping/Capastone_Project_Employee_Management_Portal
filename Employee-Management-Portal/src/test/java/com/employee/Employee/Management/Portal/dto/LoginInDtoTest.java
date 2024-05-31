package com.employee.Employee.Management.Portal.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LoginInDtoTest {

    @Test
    void testEquals() {
        // Test equality when email and password are the same
        LoginInDto login1 = new LoginInDto("user@example.com", "password123");
        LoginInDto login2 = new LoginInDto("user@example.com", "password123");
        assertEquals(login1, login2);

        // Test equality when email is different
        LoginInDto login3 = new LoginInDto("user@example.com", "password123");
        LoginInDto login4 = new LoginInDto("anotheruser@example.com", "password123");
        assertNotEquals(login3, login4);

        // Test equality when password is different
        LoginInDto login5 = new LoginInDto("user@example.com", "password123");
        LoginInDto login6 = new LoginInDto("user@example.com", "differentpassword");
        assertNotEquals(login5, login6);

        // Test equality when both email and password are different
        LoginInDto login7 = new LoginInDto("user@example.com", "password123");
        LoginInDto login8 = new LoginInDto("anotheruser@example.com", "differentpassword");
        assertNotEquals(login7, login8);
    }

    @Test
    void testHashCode() {
        // Test hash code consistency
        LoginInDto login1 = new LoginInDto("user@example.com", "password123");
        LoginInDto login2 = new LoginInDto("user@example.com", "password123");
        assertEquals(login1.hashCode(), login2.hashCode());

        // Test hash code equality when email is different
        LoginInDto login3 = new LoginInDto("user@example.com", "password123");
        LoginInDto login4 = new LoginInDto("anotheruser@example.com", "password123");
        assertNotEquals(login3.hashCode(), login4.hashCode());

        // Test hash code equality when password is different
        LoginInDto login5 = new LoginInDto("user@example.com", "password123");
        LoginInDto login6 = new LoginInDto("user@example.com", "differentpassword");
        assertNotEquals(login5.hashCode(), login6.hashCode());

        // Test hash code equality when both email and password are different
        LoginInDto login7 = new LoginInDto("user@example.com", "password123");
        LoginInDto login8 = new LoginInDto("anotheruser@example.com", "differentpassword");
        assertNotEquals(login7.hashCode(), login8.hashCode());
    }

    @Test
    void testToString() {
        LoginInDto login = new LoginInDto("user@example.com", "password123");
        String expectedString = "LoginDto{email='user@example.com', password='password123'}";
        assertEquals(expectedString, login.toString());
    }
}