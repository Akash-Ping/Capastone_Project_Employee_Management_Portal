package com.employee.Employee.Management.Portal.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RequestResourceManagerProjectDtoTest {

    @Test
    public void testGetId() {
        RequestResourceManagerProjectDto dto = new RequestResourceManagerProjectDto();
        long id = 1L;
        dto.setId(id);

        assertEquals(id, dto.getId());
    }

    @Test
    public void testSetId() {
        RequestResourceManagerProjectDto dto = new RequestResourceManagerProjectDto();
        long id = 1L;
        dto.setId(id);

        assertEquals(id, dto.getId());
    }

    @Test
    public void testGetProjectName() {
        RequestResourceManagerProjectDto dto = new RequestResourceManagerProjectDto();
        String projectName = "Project X";
        dto.setProjectName(projectName);

        assertEquals(projectName, dto.getProjectName());
    }

    @Test
    public void testSetProjectName() {
        RequestResourceManagerProjectDto dto = new RequestResourceManagerProjectDto();
        String projectName = "Project X";
        dto.setProjectName(projectName);

        assertEquals(projectName, dto.getProjectName());
    }

    @Test
    public void testHashCode() {
        RequestResourceManagerProjectDto dto1 = new RequestResourceManagerProjectDto();
        dto1.setId(1L);
        dto1.setProjectName("Project X");

        RequestResourceManagerProjectDto dto2 = new RequestResourceManagerProjectDto();
        dto2.setId(1L);
        dto2.setProjectName("Project X");

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testEquals() {
        RequestResourceManagerProjectDto dto1 = new RequestResourceManagerProjectDto();
        dto1.setId(1L);
        dto1.setProjectName("Project X");

        RequestResourceManagerProjectDto dto2 = new RequestResourceManagerProjectDto();
        dto2.setId(1L);
        dto2.setProjectName("Project X");

        RequestResourceManagerProjectDto dto3 = new RequestResourceManagerProjectDto();
        dto3.setId(2L);
        dto3.setProjectName("Project Y");

        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
    }

    @Test
    public void testToString() {
        RequestResourceManagerProjectDto dto = new RequestResourceManagerProjectDto();
        dto.setId(1L);
        dto.setProjectName("Project X");

        String expectedString = "RequestResourceManagerProjectDto(id=1, projectName=Project X)";
        assertEquals(expectedString, dto.toString());
    }
}
