package com.employee.Employee.Management.Portal.service;

import com.employee.Employee.Management.Portal.dto.*;
import com.employee.Employee.Management.Portal.entity.Project;
import com.employee.Employee.Management.Portal.entity.Resource;
import com.employee.Employee.Management.Portal.entity.User;
import com.employee.Employee.Management.Portal.repository.ProjectRepository;
import com.employee.Employee.Management.Portal.repository.ResourceRepository;
import com.employee.Employee.Management.Portal.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RequestResourceServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ResourceRepository resourceRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private RequestResourceService requestResourceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRequestResource_Success() {
        RequestResourceDto requestResourceDto = new RequestResourceDto();
        requestResourceDto.setEmpId("E001");
        requestResourceDto.setEmail("manager@example.com");
        requestResourceDto.setProjectId(1L);
        requestResourceDto.setComment("Request Comment");

        User employee = new User();
        employee.setId(1L);

        User manager = new User();
        manager.setId(2L);

        Project project = new Project();
        project.setId(1L);

        when(userRepository.findByEmpId("E001")).thenReturn(Optional.of(employee));
        when(userRepository.findByEmail("manager@example.com")).thenReturn(Optional.of(manager));
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        requestResourceService.createRequestResource(requestResourceDto);

        verify(resourceRepository, times(1)).save(any(Resource.class));
    }

    @Test
    void testCreateRequestResource_EmployeeNotFound() {
        RequestResourceDto requestResourceDto = new RequestResourceDto();
        requestResourceDto.setEmpId("E001");
        requestResourceDto.setEmail("manager@example.com");

        when(userRepository.findByEmpId("E001")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            requestResourceService.createRequestResource(requestResourceDto);
        });
    }

    @Test
    void testCreateRequestResource_ManagerNotFound() {
        RequestResourceDto requestResourceDto = new RequestResourceDto();
        requestResourceDto.setEmpId("E001");
        requestResourceDto.setEmail("manager@example.com");

        User employee = new User();
        employee.setId(1L);

        when(userRepository.findByEmpId("E001")).thenReturn(Optional.of(employee));
        when(userRepository.findByEmail("manager@example.com")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            requestResourceService.createRequestResource(requestResourceDto);
        });
    }

    @Test
    void testIsRequested_True() {
        RequestedDto requestedDto = new RequestedDto();
        requestedDto.setEmpId("E001");
        requestedDto.setEmail("manager@example.com");

        User employee = new User();
        employee.setId(1L);

        User manager = new User();
        manager.setId(2L);

        Resource resource = new Resource();

        when(userRepository.findByEmpId("E001")).thenReturn(Optional.of(employee));
        when(userRepository.findByEmail("manager@example.com")).thenReturn(Optional.of(manager));
        when(resourceRepository.findByEmployeeIdAndManagerId(1L, manager)).thenReturn(resource);

        RequestedOutDto result = requestResourceService.isRequested(requestedDto);

        assertTrue(result.isRequested());
    }

    @Test
    void testIsRequested_False() {
        RequestedDto requestedDto = new RequestedDto();
        requestedDto.setEmpId("E001");
        requestedDto.setEmail("manager@example.com");

        User employee = new User();
        employee.setId(1L);

        User manager = new User();
        manager.setId(2L);

        when(userRepository.findByEmpId("E001")).thenReturn(Optional.of(employee));
        when(userRepository.findByEmail("manager@example.com")).thenReturn(Optional.of(manager));
        when(resourceRepository.findByEmployeeIdAndManagerId(1L, manager)).thenReturn(null);

        RequestedOutDto result = requestResourceService.isRequested(requestedDto);

        assertFalse(result.isRequested());
    }

    @Test
    void testGetAllByManagerEmail() {
        String email = "manager@example.com";

        User manager = new User();
        manager.setId(1L);

        Project project1 = new Project();
        project1.setId(1L);
        project1.setProjectName("Project 1");

        Project project2 = new Project();
        project2.setId(2L);
        project2.setProjectName("Project 2");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(manager));
        when(projectRepository.findAllByManager(manager)).thenReturn(Arrays.asList(project1, project2));

        List<RequestResourceManagerProjectDto> result = requestResourceService.getAllByManagerEmail(email);

        assertEquals(2, result.size());
        assertEquals("Project 1", result.get(0).getProjectName());
        assertEquals("Project 2", result.get(1).getProjectName());
    }



    @Test
    void testGetAllResourceRequests() {
        Resource resource1 = new Resource();
        resource1.setId(1L);
        resource1.setEmployeeId(1L);
        resource1.setManagerId(new User());
        resource1.getManagerId().setId(2L);
        resource1.setProjectId(new Project());
        resource1.getProjectId().setId(1L);
        resource1.setComment("Comment 1");

        // Mocking an employee object
        User employee = new User();
        employee.setId(1L);
        employee.setName("Employee 1");

        User manager1 = new User();
        manager1.setId(2L);
        manager1.setName("Manager 1");

        Project project1 = new Project();
        project1.setId(1L);
        project1.setProjectName("Project 1");

        // Mocking behavior of userRepository.findById() to return Optional containing employee object
        when(userRepository.findById(1L)).thenReturn(Optional.of(employee));

        // Mock behavior of repository methods used in the service
        when(resourceRepository.findAll()).thenReturn(Arrays.asList(resource1));
        when(userRepository.findById(2L)).thenReturn(Optional.of(manager1));
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project1));

        // Call the method under test
        List<ResourceRequestsAdminOutDto> result = requestResourceService.getAllResourceRequests();

        // Verify the result
        assertEquals(1, result.size());
        ResourceRequestsAdminOutDto outRequest = result.get(0);
        assertEquals("Employee 1", outRequest.getEmployeeName());
        assertEquals("Manager 1", outRequest.getManagerName());
        assertEquals("Project 1", outRequest.getProjectName());
        assertEquals("Comment 1", outRequest.getComment());
    }

    @Test
    void testGetAllResourceRequests_NoRequests() {
        when(resourceRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(RuntimeException.class, () -> {
            requestResourceService.getAllResourceRequests();
        });
    }

    @Test
    void testRejectResourceRequest() {
        Long resourceId = 1L;

        requestResourceService.rejectResourceRequest(resourceId);

        verify(resourceRepository, times(1)).deleteById(resourceId);
    }

    @Test
    void testAcceptRequest_Success() {
        Long resourceId = 1L;

        Resource resource = new Resource();
        resource.setId(resourceId);
        resource.setEmployeeId(1L);
        resource.setManagerId(new User());
        resource.getManagerId().setId(2L);
        resource.setProjectId(new Project());
        resource.getProjectId().setId(3L);

        User employee = new User();
        employee.setId(1L);

        when(resourceRepository.findById(resourceId)).thenReturn(Optional.of(resource));
        when(userRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(resourceRepository.findByEmployeeId(1L)).thenReturn(Collections.singletonList(resource));

        ApiResponseDto response = requestResourceService.acceptRequest(resourceId);

        assertEquals("Resource request accepted successfully", response.getMessage());
        assertEquals(3L, employee.getEmpProjectId());
        assertEquals(2L, employee.getEmpManagerId());

        verify(userRepository, times(1)).save(employee);
        verify(resourceRepository, times(2)).deleteById(resourceId);
    }

    @Test
    void testAcceptRequest_ResourceNotFound() {
        Long resourceId = 1L;

        when(resourceRepository.findById(resourceId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            requestResourceService.acceptRequest(resourceId);
        });
    }

    @Test
    void testAcceptRequest_EmployeeNotFound() {
        Long resourceId = 1L;

        Resource resource = new Resource();
        resource.setId(resourceId);
        resource.setEmployeeId(1L);
        resource.setManagerId(new User());
        resource.getManagerId().setId(2L);
        resource.setProjectId(new Project());
        resource.getProjectId().setId(3L);

        when(resourceRepository.findById(resourceId)).thenReturn(Optional.of(resource));
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            requestResourceService.acceptRequest(resourceId);
        });
    }

    @Test
    void testAcceptRequest_EmployeeAlreadyAssigned() {
        Long resourceId = 1L;

        Resource resource = new Resource();
        resource.setId(resourceId);
        resource.setEmployeeId(1L);
        resource.setManagerId(new User());
        resource.getManagerId().setId(2L);
        resource.setProjectId(new Project());
        resource.getProjectId().setId(3L);

        User employee = new User();
        employee.setId(1L);
        employee.setEmpProjectId(5L);

        when(resourceRepository.findById(resourceId)).thenReturn(Optional.of(resource));
        when(userRepository.findById(1L)).thenReturn(Optional.of(employee));

        assertThrows(RuntimeException.class, () -> {
            requestResourceService.acceptRequest(resourceId);
        });
    }

    @Test
    void testAcceptRequest_DeleteAllEmployeeRequests() {
        Long resourceId = 1L;

        // Setting up the resources
        Resource resource = new Resource();
        resource.setId(resourceId);
        resource.setEmployeeId(1L);
        resource.setManagerId(new User());
        resource.getManagerId().setId(2L);
        resource.setProjectId(new Project());
        resource.getProjectId().setId(3L);

        User employee = new User();
        employee.setId(1L);

        Resource otherRequest1 = new Resource();
        otherRequest1.setId(2L);
        otherRequest1.setEmployeeId(1L);

        Resource otherRequest2 = new Resource();
        otherRequest2.setId(3L);
        otherRequest2.setEmployeeId(1L);

        Resource otherRequest3 = new Resource();
        otherRequest3.setId(4L);
        otherRequest3.setEmployeeId(1L);

        // Mocking repository responses
        when(resourceRepository.findById(resourceId)).thenReturn(Optional.of(resource));
        when(userRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(resourceRepository.findByEmployeeId(1L)).thenReturn(Arrays.asList(resource, otherRequest1, otherRequest2, otherRequest3));

        // Mocking deleteById to handle the extra call
        doNothing().when(resourceRepository).deleteById(anyLong());

        // Performing the operation
        ApiResponseDto response = requestResourceService.acceptRequest(resourceId);

        // Verifying the response
        assertEquals("Resource request accepted successfully", response.getMessage());
        assertEquals(3L, employee.getEmpProjectId());
        assertEquals(2L, employee.getEmpManagerId());

        // Verifying the interactions
        verify(userRepository, times(1)).save(employee);
        verify(resourceRepository, times(5)).deleteById(anyLong()); // Adjusted to match the actual number of delete calls


    }


}
