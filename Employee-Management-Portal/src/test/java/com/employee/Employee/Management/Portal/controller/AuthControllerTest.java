package com.employee.Employee.Management.Portal.controller;


import com.employee.Employee.Management.Portal.config.JwtProvider;
import com.employee.Employee.Management.Portal.entity.Role;
import com.employee.Employee.Management.Portal.entity.User;
import com.employee.Employee.Management.Portal.repository.UserRepository;
import com.employee.Employee.Management.Portal.service.CustomUserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private JwtProvider jwtProvider;

    @MockBean
    private CustomUserDetails customUserDetails;


    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("john.doe@example.com");
        user.setPassword("password123"); // Encrypted password for "password"
        user.setEmpId("1234");
        user.setRole(Role.EMPLOYEE);

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username("john.doe@example.com")
                .password("password123")
                .authorities(Role.EMPLOYEE.name())
                .build();

        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(user));
        Mockito.when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        Mockito.when(customUserDetails.loadUserByUsername(anyString())).thenReturn(userDetails);
        Mockito.when(jwtProvider.generateToken(Mockito.any())).thenReturn("dummy-jwt-token");
    }

    @Test
    void signin_Success() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"email\": \"john.doe@example.com\", \"password\": \"password123\" }"));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Login successful"))
                .andExpect(jsonPath("$.jwt").value("dummy-jwt-token"))
                .andExpect(jsonPath("$.empId").value("1234"))
                .andExpect(jsonPath("$.role").value("EMPLOYEE"));
    }
}