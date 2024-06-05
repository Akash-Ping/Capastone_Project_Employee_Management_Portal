package com.employee.Employee.Management.Portal.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class ProjectDtoTest {

    @Test
    public void testGetterSetter() {
        ProjectDto projectDto = createProjectDto();
        assertEquals(1L, projectDto.getId());
        assertEquals("Project X", projectDto.getProjectName());
        assertEquals("Description of Project X", projectDto.getDescription());
        assertEquals("2024-01-01", projectDto.getStartDate());
        assertEquals(123L, projectDto.getManager());
        assertEquals("John Doe", projectDto.getHead());
        assertEquals(Arrays.asList("Alice", "Bob", "Charlie"), projectDto.getTeamMembers());
    }

    @Test
    public void testEqualsWithSettersAndGetters() {
        ProjectDto projectDto1 = createProjectDto();
        ProjectDto projectDto2 = createProjectDto();
        ProjectDto projectDto3 = createDifferentProjectDto();
        assertTrue(projectDto1.equals(projectDto2));
        assertFalse(projectDto1.equals(projectDto3));
    }

    @Test
    public void testHashCodeWithSettersAndGetters() {
        ProjectDto projectDto1 = createProjectDto();
        ProjectDto projectDto2 = createProjectDto();
        assertEquals(projectDto1.hashCode(), projectDto2.hashCode());
    }

    private ProjectDto createProjectDto() {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(1L);
        projectDto.setProjectName("Project X");
        projectDto.setDescription("Description of Project X");
        projectDto.setStartDate("2024-01-01");
        projectDto.setManager(123L);
        projectDto.setHead("John Doe");
        projectDto.setTeamMembers(Arrays.asList("Alice", "Bob", "Charlie"));
        return projectDto;
    }

    private ProjectDto createDifferentProjectDto() {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(2L);
        projectDto.setProjectName("Project Y");
        projectDto.setDescription("Description of Project Y");
        projectDto.setStartDate("2024-02-01");
        projectDto.setManager(456L);
        projectDto.setHead("Jane Smith");
        projectDto.setTeamMembers(Arrays.asList("David", "Eve", "Frank"));
        return projectDto;
    }
}
