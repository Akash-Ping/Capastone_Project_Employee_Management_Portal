package com.employee.Employee.Management.Portal.controller;

import com.employee.Employee.Management.Portal.dto.*;
import com.employee.Employee.Management.Portal.service.RequestResourceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ResourceControllerTest {

    @Mock
    private RequestResourceService requestResourceService;

    @InjectMocks
    private ResourceController resourceController;

    private MockMvc mockMvc;

    @Test
    void testCreateRequestResource() throws Exception {
        RequestResourceDto requestResourceDto = new RequestResourceDto();
        requestResourceDto.setEmpId("emp123");
        requestResourceDto.setEmail("test@example.com");
        requestResourceDto.setProjectId(1L);
        requestResourceDto.setComment("Resource type comment");

        ApiResponseDto expectedResponse = new ApiResponseDto();
        expectedResponse.setMessage("Requested resource successfully");

        doNothing().when(requestResourceService).createRequestResource(any(RequestResourceDto.class));

        mockMvc = MockMvcBuilders.standaloneSetup(resourceController).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/requestResource/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"empId\":\"emp123\", \"email\":\"test@example.com\", \"projectId\":1, \"comment\":\"Resource type comment\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Requested resource successfully"));
    }

    @Test
    void testIsRequested() throws Exception {
        RequestedDto requestedDto = new RequestedDto();
        // Set requestedDto properties here

        RequestedOutDto expectedResponse = new RequestedOutDto();
        // Set expectedResponse properties here

        when(requestResourceService.isRequested(any(RequestedDto.class))).thenReturn(expectedResponse);

        mockMvc = MockMvcBuilders.standaloneSetup(resourceController).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/requestResource/isRequested")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"propertyName\":\"propertyValue\"}")) // Set content here
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetAllByManagerEmail() throws Exception {
        String email = "example@example.com";

        List<RequestResourceManagerProjectDto> projectList = new ArrayList<>();
        // Add project DTOs to projectList

        when(requestResourceService.getAllByManagerEmail(email)).thenReturn(projectList);

        mockMvc = MockMvcBuilders.standaloneSetup(resourceController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/getAll/project/byManager/{email}", email))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray()); // Check other expectations for the response
    }

    @Test
    void testGetAllResourceRequests() throws Exception {
        List<ResourceRequestsAdminOutDto> outDtoList = new ArrayList<>();
        // Add ResourceRequestsAdminOutDto objects to outDtoList

        when(requestResourceService.getAllResourceRequests()).thenReturn(outDtoList);

        mockMvc = MockMvcBuilders.standaloneSetup(resourceController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/requestResource/getAll/requests"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray()); // Check other expectations for the response
    }

    @Test
    void testRejectResourceRequest() throws Exception {
        Long id = 1L;

        ApiResponseDto expectedResponse = new ApiResponseDto();
        expectedResponse.setMessage("Resource request rejected successfully");

        doNothing().when(requestResourceService).rejectResourceRequest(id);

        mockMvc = MockMvcBuilders.standaloneSetup(resourceController).build();

        mockMvc.perform(MockMvcRequestBuilders.delete("/requestResource/reject/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Resource request rejected successfully"));
    }

    @Test
    void testAcceptRequest() throws Exception {
        Long id = 1L;

        ApiResponseDto expectedResponse = new ApiResponseDto();
        // Set expectedResponse properties here

        when(requestResourceService.acceptRequest(id)).thenReturn(expectedResponse);

        mockMvc = MockMvcBuilders.standaloneSetup(resourceController).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/request/accept/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(expectedResponse.getMessage())); // Check other expectations for the response
    }



    @Test
    void testGetAllByManagerEmail_NoProjectsFound() throws Exception {
        String email = "example@example.com";

        List<RequestResourceManagerProjectDto> projectList = new ArrayList<>();
        // Set projectList as empty

        when(requestResourceService.getAllByManagerEmail(email)).thenReturn(projectList);

        mockMvc = MockMvcBuilders.standaloneSetup(resourceController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/getAll/project/byManager/{email}", email))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty()); // Check for empty list
    }

    @Test
    void testIsRequested_ResourceNotFound() throws Exception {
        RequestedDto requestedDto = new RequestedDto();
        // Set requestedDto properties here

        RequestedOutDto responseDto = new RequestedOutDto();
        responseDto.setRequested(false); // Resource not requested

        when(requestResourceService.isRequested(any(RequestedDto.class))).thenReturn(responseDto);

        mockMvc = MockMvcBuilders.standaloneSetup(resourceController).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/requestResource/isRequested")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"propertyName\":\"propertyValue\"}")) // Set content here
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.requested").value(false)); // Check for resource not requested
    }



}
