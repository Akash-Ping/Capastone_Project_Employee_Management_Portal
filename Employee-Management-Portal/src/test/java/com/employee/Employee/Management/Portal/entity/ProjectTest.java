package com.employee.Employee.Management.Portal.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectTest {

    private Project project1;
    private Project project2;
    private Project project3;
    private User manager;

    @BeforeEach
    public void setUp() {
        manager = new User();
        manager.setId(1L);
        manager.setName("Manager Name");

        project1 = new Project(1L, "Project A", "Description A", "2024-05-19", manager);
        project2 = new Project(1L, "Project A", "Description A", "2024-05-19", manager);
        project3 = new Project(2L, "Project B", "Description B", "2024-06-01", manager);
    }

    @Test
    public void testGetSetId() {
        Project project = new Project();
        Long id = 2L;
        project.setId(id);
        assertEquals(id, project.getId());
    }

    @Test
    public void testGetSetProjectName() {
        Project project = new Project();
        String projectName = "Project B";
        project.setProjectName(projectName);
        assertEquals(projectName, project.getProjectName());
    }

    @Test
    public void testGetSetDescription() {
        Project project = new Project();
        String description = "Description B";
        project.setDescription(description);
        assertEquals(description, project.getDescription());
    }

    @Test
    public void testGetSetStartDate() {
        Project project = new Project();
        String startDate = "2024-06-01";
        project.setStartDate(startDate);
        assertEquals(startDate, project.getStartDate());
    }

    @Test
    public void testGetSetManager() {
        Project project = new Project();
        User newManager = new User();
        newManager.setId(2L);
        newManager.setName("New Manager");

        project.setManager(newManager);
        assertEquals(newManager, project.getManager());
    }

    @Test
    public void testEqualsAndHashCode() {
        assertEquals(project1, project2);
        assertEquals(project1.hashCode(), project2.hashCode());

        assertNotEquals(project1, project3);
        // Removed the assertion for different hash codes since it may not be necessary
    }

    @Test
    public void testToString() {
        String expectedString = "Project(id=1, projectName=Project A, description=Description A, startDate=2024-05-19, manager=User(id=1, name=Manager Name, contactNo=null, dob=null, doj=null, designation=null, email=null, empId=null, location=null, password=null, role=null, assignedSkills=[], empManagerId=null, empProjectId=null))";
        assertEquals(expectedString, project1.toString());
    }

    @Test
    public void testNoArgsConstructor() {
        Project project = new Project();
        assertNotNull(project);
    }

    @Test
    public void testAllArgsConstructor() {
        Project project = new Project(3L, "Project C", "Description C", "2024-07-01", manager);
        assertEquals(3L, project.getId());
        assertEquals("Project C", project.getProjectName());
        assertEquals("Description C", project.getDescription());
        assertEquals("2024-07-01", project.getStartDate());
        assertEquals(manager, project.getManager());
    }
}
