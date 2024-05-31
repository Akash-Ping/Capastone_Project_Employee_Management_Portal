package com.employee.Employee.Management.Portal.controller;

import com.employee.Employee.Management.Portal.dto.ApiResponseDto;
import com.employee.Employee.Management.Portal.dto.EmpDto;
import com.employee.Employee.Management.Portal.dto.RegisterDto;
import com.employee.Employee.Management.Portal.dto.UpdateSkillDto;
import com.employee.Employee.Management.Portal.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @Test
    void testGetEmployeeByEmail() throws Exception {
        String email = "test@example.com";
        EmpDto expectedDto = new EmpDto();
        expectedDto.setEmail(email);
        expectedDto.setName("John Doe");

        when(employeeService.getEmployee(email)).thenReturn(expectedDto);

        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

        mockMvc.perform(get("/api/employee/{email}", email))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testUpdateSkillsOfEmployee() throws Exception {
        /// need to check this test
        String userId = "N0001";
        Set<Long> skillIdsToAdd = new HashSet<>();
        skillIdsToAdd.add(1L);
        Set<Long> skillIdsToRemove = new HashSet<>();
        skillIdsToRemove.add(2L);

        UpdateSkillDto updateSkillDto = new UpdateSkillDto();
        updateSkillDto.setSkillIdsToAdd(skillIdsToAdd);
        updateSkillDto.setSkillIdsToRemove(skillIdsToRemove);

        ApiResponseDto expectedResponse = new ApiResponseDto("User skills updated successfully");

        when(employeeService.updateSkill(eq(userId), anySet(), anySet())).thenReturn(expectedResponse);

        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

        mockMvc.perform(put("/api/employee/{userId}/updateskills", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"skillIdsToAdd\":[1],\"skillIdsToRemove\":[2]}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("User skills updated successfully"));
    }

    @Test
    void testUpdateSkillsOfEmployee_NoSkillsAddedOrRemoved() throws Exception {
        String userId = "N0001";

        UpdateSkillDto updateSkillDto = new UpdateSkillDto();

        ApiResponseDto expectedResponse = new ApiResponseDto("User skills updated successfully");

        when(employeeService.updateSkill(eq(userId), anySet(), anySet())).thenReturn(expectedResponse);

        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

        mockMvc.perform(put("/api/employee/{userId}/updateskills", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"skillIdsToAdd\":[],\"skillIdsToRemove\":[]}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("User skills updated successfully"));
    }

    @Test
    void testUpdatePassword() throws Exception {
        String email = "test@example.com";
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail(email);
        registerDto.setPassword("newPassword");

        ApiResponseDto expectedResponse = new ApiResponseDto("Password updated successfully");

        when(employeeService.updatePassword(eq(email), any(RegisterDto.class))).thenReturn(expectedResponse);

        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

        mockMvc.perform(put("/api/employee/{email}/updatepassword", email)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"password\":\"newPassword\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Password updated successfully"));
    }

}
