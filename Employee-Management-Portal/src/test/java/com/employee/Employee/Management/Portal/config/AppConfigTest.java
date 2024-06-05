package com.employee.Employee.Management.Portal.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class AppConfigTest {

    @Test
    public void testPasswordEncoderNotNull() {
        // Create an instance of AppConfig
        AppConfig appConfig = new AppConfig();

        // Call the passwordEncoder() method
        PasswordEncoder passwordEncoder = appConfig.passwordEncoder();

        // Assert that the PasswordEncoder object is not null
        assertNotNull(passwordEncoder);
    }

    @Test
    public void testPasswordEncoderConsistency() {
        // Create an instance of AppConfig
        AppConfig appConfig = new AppConfig();

        // Call the passwordEncoder() method
        PasswordEncoder passwordEncoder = appConfig.passwordEncoder();

        // Define a raw password
        String rawPassword = "mySecretPassword";

        // Encode the raw password twice
        String encodedPassword1 = passwordEncoder.encode(rawPassword);
        String encodedPassword2 = passwordEncoder.encode(rawPassword);

        // Assert that the two encoded passwords are not equal
        assertNotEquals(encodedPassword1, encodedPassword2);

        // Assert that the raw password matches the encoded passwords
        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword1));
        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword2));
    }

    @Test
    public void testPasswordEncoderReturnsBCryptPasswordEncoder() {
        // Create an instance of AppConfig
        AppConfig appConfig = new AppConfig();

        // Call the passwordEncoder() method
        PasswordEncoder passwordEncoder = appConfig.passwordEncoder();

        // Assert that the returned PasswordEncoder is an instance of BCryptPasswordEncoder
        assertTrue(passwordEncoder instanceof BCryptPasswordEncoder);
    }
}
