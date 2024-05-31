package com.employee.Employee.Management.Portal.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class ResourceRequestsAdminOutDtoTest {

    private ResourceRequestsAdminOutDto dto1;
    private ResourceRequestsAdminOutDto dto2;

    @BeforeEach
    public void setUp() {
        dto1 = new ResourceRequestsAdminOutDto();
        dto1.setId(1L);
        dto1.setEmployeeId(100L);
        dto1.setEmployeeName("John Doe");
        dto1.setManagerName("Manager A");
        dto1.setProjectName("Project X");
        dto1.setComment("Request Comment");
        dto1.setManagerId(200L);
        dto1.setProjectId(300L);

        dto2 = new ResourceRequestsAdminOutDto();
        dto2.setId(1L);
        dto2.setEmployeeId(100L);
        dto2.setEmployeeName("John Doe");
        dto2.setManagerName("Manager A");
        dto2.setProjectName("Project X");
        dto2.setComment("Request Comment");
        dto2.setManagerId(200L);
        dto2.setProjectId(300L);
    }

    @Test
    public void testGetSetId() {
        ResourceRequestsAdminOutDto dto = new ResourceRequestsAdminOutDto();
        Long id = 123L;
        dto.setId(id);
        assertEquals(id, dto.getId());
    }

    @Test
    public void testGetSetEmployeeId() {
        ResourceRequestsAdminOutDto dto = new ResourceRequestsAdminOutDto();
        Long employeeId = 456L;
        dto.setEmployeeId(employeeId);
        assertEquals(employeeId, dto.getEmployeeId());
    }

    @Test
    public void testGetSetEmployeeName() {
        ResourceRequestsAdminOutDto dto = new ResourceRequestsAdminOutDto();
        String employeeName = "Jane Doe";
        dto.setEmployeeName(employeeName);
        assertEquals(employeeName, dto.getEmployeeName());
    }

    @Test
    public void testGetSetManagerName() {
        ResourceRequestsAdminOutDto dto = new ResourceRequestsAdminOutDto();
        String managerName = "Manager B";
        dto.setManagerName(managerName);
        assertEquals(managerName, dto.getManagerName());
    }

    @Test
    public void testGetSetProjectName() {
        ResourceRequestsAdminOutDto dto = new ResourceRequestsAdminOutDto();
        String projectName = "Project XYZ";
        dto.setProjectName(projectName);
        assertEquals(projectName, dto.getProjectName());
    }

    @Test
    public void testGetSetComment() {
        ResourceRequestsAdminOutDto dto = new ResourceRequestsAdminOutDto();
        String comment = "Request for additional resources.";
        dto.setComment(comment);
        assertEquals(comment, dto.getComment());
    }

    @Test
    public void testGetSetManagerId() {
        ResourceRequestsAdminOutDto dto = new ResourceRequestsAdminOutDto();
        Long managerId = 789L;
        dto.setManagerId(managerId);
        assertEquals(managerId, dto.getManagerId());
    }

    @Test
    public void testGetSetProjectId() {
        ResourceRequestsAdminOutDto dto = new ResourceRequestsAdminOutDto();
        Long projectId = 101L;
        dto.setProjectId(projectId);
        assertEquals(projectId, dto.getProjectId());
    }

    @Test
    public void testHashCode() {
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testEquals() {
        assertEquals(dto1, dto2);

        ResourceRequestsAdminOutDto dto3 = new ResourceRequestsAdminOutDto();
        dto3.setId(2L);
        dto3.setEmployeeId(101L);
        dto3.setEmployeeName("Alice Smith");
        dto3.setManagerName("Manager C");
        dto3.setProjectName("Project ABC");
        dto3.setComment("Different Comment");
        dto3.setManagerId(201L);
        dto3.setProjectId(301L);

        assertNotEquals(dto1, dto3);
    }

    @Test
    public void testToString() {
        String expectedString = "ResourceRequestsAdminOutDto{id=1, employeeId=100, employeeName='John Doe', managerName='Manager A', projectName='Project X', comment='Request Comment', managerId=200, projectId=300}";
        assertEquals(expectedString, dto1.toString());
    }
}
