package com.employee.Employee.Management.Portal.controller;


import com.employee.Employee.Management.Portal.dto.ApiResponseDto;
import com.employee.Employee.Management.Portal.dto.LoginInDto;
import com.employee.Employee.Management.Portal.dto.LoginOutDto;
import com.employee.Employee.Management.Portal.dto.RegisterDto;
import com.employee.Employee.Management.Portal.entity.Role;
import com.employee.Employee.Management.Portal.service.RegisterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RegisterControllerTest {

    @Mock
    private RegisterService registerService;

    @InjectMocks
    private RegisterController registerController;

    private MockMvc mockMvc;

    @Test
    void testRegisterAdmin() throws Exception {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail("admin@nucleusteq.com");
        registerDto.setName("John Doe");

        ApiResponseDto expectedResponse = new ApiResponseDto("Admin registered successfully");

        when(registerService.adminRegister(any(RegisterDto.class))).thenReturn(expectedResponse);

        mockMvc = MockMvcBuilders.standaloneSetup(registerController).build();

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"admin@nucleusteq.com\",\"name\":\"John Doe\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Admin registered successfully"));
    }

//    @Test
//    void testLoginUser() throws Exception {
//        LoginInDto loginInDto = new LoginInDto();
//        loginInDto.setEmail("test@example.com");
//        loginInDto.setPassword("password");
//
//        LoginOutDto expectedResponse = new LoginOutDto();
//        expectedResponse.setMessage("Login successful");
//        expectedResponse.setRole(Role.EMPLOYEE); // Assuming the role is EMPLOYEE for login
//
//        when(registerService.userLogin(any(LoginInDto.class))).thenReturn(expectedResponse);
//
//        mockMvc = MockMvcBuilders.standaloneSetup(registerController).build();
//
//        mockMvc.perform(post("/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"email\":\"test@example.com\",\"password\":\"password\"}"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.message").value("Login successful"))
//                .andExpect(jsonPath("$.role").value("EMPLOYEE")); // Ensure role value is returned as a String
//    }
}




