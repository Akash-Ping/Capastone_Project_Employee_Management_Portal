package com.employee.Employee.Management.Portal.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.employee.Employee.Management.Portal.dto.*;
import com.employee.Employee.Management.Portal.entity.Skills;
import com.employee.Employee.Management.Portal.entity.User;
import com.employee.Employee.Management.Portal.repository.UserRepository;
import com.employee.Employee.Management.Portal.service.AdminService;
import com.employee.Employee.Management.Portal.service.RegisterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc (addFilters = false)
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService adminService;

    @MockBean
    private RegisterService registerService;

    @MockBean
    private UserRepository userRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetAllEmployee() throws Exception {
        List<RegisterOutDto> users = new ArrayList<>();
        when(adminService.getAllEmployee()).thenReturn(users);

        mockMvc.perform(get("/employee/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(users.size()));

        MvcResult mvcResult = mockMvc.perform(get("/employee/all")).andReturn();
        String requestURI = mvcResult.getRequest().getRequestURI();
        assertEquals("/employee/all", requestURI);
    }

    @Test
    public void testGetAllSkills() throws Exception {
        List<Skills> skills = new ArrayList<>();
        when(registerService.getAllSkills()).thenReturn(skills);

        mockMvc.perform(get("/employee/allSkills"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(skills.size()));

        MvcResult mvcResult = mockMvc.perform(get("/employee/allSkills")).andReturn();
        String requestURI = mvcResult.getRequest().getRequestURI();
        assertEquals("/employee/allSkills", requestURI);
    }

    @Test
    public void testAddUser() throws Exception {
        ApiResponseDto response = new ApiResponseDto("User added successfully");
        when(registerService.addUser(any(RegisterDto.class))).thenReturn(response);

        String jsonRequest = objectMapper.writeValueAsString(new RegisterDto());
        mockMvc.perform(post("/employee/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User added successfully"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        ApiResponseDto response = new ApiResponseDto("User updated successfully");
        when(registerService.updateUser(any(RegisterDto.class))).thenReturn(response);

        String jsonRequest = objectMapper.writeValueAsString(new RegisterDto());
        mockMvc.perform(put("/employee/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User updated successfully"));
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        List<RegisterOutDto> employees = new ArrayList<>();
        when(adminService.getAllEmployee()).thenReturn(employees);

        mockMvc.perform(get("/employee/getAllEmployees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(employees.size()));
    }

    @Test
    public void testGetEmployeesSkill() throws Exception {
        // Prepare the skills to be returned
        Skills skill1 = new Skills();
        skill1.setId(1L);
        skill1.setSkillName("Java");

        Skills skill2 = new Skills();
        skill2.setId(2L);
        skill2.setSkillName("Spring");

        Set<Skills> skills = new HashSet<>();
        skills.add(skill1);
        skills.add(skill2);

        SkillsOutDto skillsOutDto = new SkillsOutDto();
        skillsOutDto.setAssignedSkills(skills);

        // Mock the service call
        when(adminService.getEmployeeId("1")).thenReturn(skillsOutDto);

        // Perform the GET request and assert the results
        mockMvc.perform(get("/employee/Employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.assignedSkills").isArray())
                .andExpect(jsonPath("$.assignedSkills.length()").value(2))
                .andExpect(jsonPath("$.assignedSkills[0].id").exists())
                .andExpect(jsonPath("$.assignedSkills[0].skillName").exists());
    }

    @Test
    public void testGetAllEmployeesAndManagers() throws Exception {
        List<RegisterOutDto> employeesAndManagers = new ArrayList<>();
        when(adminService.getAllEmployeeAndManager()).thenReturn(employeesAndManagers);

        mockMvc.perform(get("/employee/getAllEmployeesAndManagers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(employeesAndManagers.size()));
    }

    @Test
    public void testGetAllManagers() throws Exception {
        List<ManagerOutDto> managers = new ArrayList<>();
        when(adminService.getAllManagers()).thenReturn(managers);

        mockMvc.perform(get("/employee/getAllManagers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(managers.size()));
    }

    @Test
    public void testGetAllManagersInfo() throws Exception {
        List<ManagerInfoDto> managerInfoList = new ArrayList<>();
        when(adminService.getAllManagersInfo()).thenReturn(managerInfoList);

        mockMvc.perform(get("/employee/getAllManagersInfo"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(managerInfoList.size()));
    }

    @Test
    public void testAddProject() throws Exception {
        ApiResponseDto response = new ApiResponseDto("Project added successfully");
        when(adminService.addProject(any(ProjectDto.class))).thenReturn(response);

        String jsonRequest = objectMapper.writeValueAsString(new ProjectDto());
        mockMvc.perform(post("/employee/addProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Project added successfully"));
    }

    @Test
    public void testGetAllProjects() throws Exception {
        List<ProjectDto> projectDtoList = new ArrayList<>();
        when(adminService.getAllProjects()).thenReturn(projectDtoList);

        mockMvc.perform(get("/employee/getAllProjects"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(projectDtoList.size()));
    }

    @Test
    public void testGetAllProjectsForAssign() throws Exception {
        List<AssignProjectOutDto> assignProjectOutDtoList = new ArrayList<>();
        when(adminService.getAllProjectsForAssign()).thenReturn(assignProjectOutDtoList);

        mockMvc.perform(get("/employee/getAllProjectsForAssign"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(assignProjectOutDtoList.size()));
    }

    @Test
    public void testGetAllByManagerId() throws Exception {
        List<ProjectOutDto> projectOutDtoList = new ArrayList<>();
        when(adminService.getAllByManagerId(1L)).thenReturn(projectOutDtoList);

        mockMvc.perform(get("/employee/getAll/project/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(projectOutDtoList.size()));
    }

    @Test
    public void testAssignProject() throws Exception {
        ApiResponseDto response = new ApiResponseDto("Project assigned successfully");
        when(adminService.assignProject(any(AssignProjectDto.class))).thenReturn(response);

        String jsonRequest = objectMapper.writeValueAsString(new AssignProjectDto());
        mockMvc.perform(post("/employee/assignProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Project assigned successfully"));
    }

    @Test
    public void testGetFilteredUsers() throws Exception {
        List<UserDto> filteredUsers = new ArrayList<>();
        when(adminService.getFilteredUsers(any(FilterDto.class))).thenReturn(filteredUsers);

        String jsonRequest = objectMapper.writeValueAsString(new FilterDto());
        mockMvc.perform(post("/employee/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(filteredUsers.size()));
    }

    @Test
    public void testUnassignProject() throws Exception {
        String empId = "EMP001";
        mockMvc.perform(put("/employee/unassignProject/{empId}", empId))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUser() throws Exception {
        Long userId = 1L;
        mockMvc.perform(delete("/employee/delete/{id}", userId))
                .andExpect(status().isOk());
    }
}
