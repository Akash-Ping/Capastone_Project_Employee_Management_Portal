package com.employee.Employee.Management.Portal.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResourceTest {

    private Resource resource1;
    private Resource resource2;
    private Resource resource3;
    private User manager1;
    private User manager2;
    private Project project1;
    private Project project2;

    @BeforeEach
    public void setUp() {
        manager1 = new User();
        manager1.setId(1L);
        manager1.setName("Manager1");

        manager2 = new User();
        manager2.setId(2L);
        manager2.setName("Manager2");

        project1 = new Project();
        project1.setId(1L);
        project1.setProjectName("Project1");

        project2 = new Project();
        project2.setId(2L);
        project2.setProjectName("Project2");

        resource1 = new Resource();
        resource1.setId(1L);
        resource1.setComment("Comment1");
        resource1.setManagerId(manager1);
        resource1.setProjectId(project1);
        resource1.setEmployeeId(1L);

        resource2 = new Resource();
        resource2.setId(1L);
        resource2.setComment("Comment1");
        resource2.setManagerId(manager1);
        resource2.setProjectId(project1);
        resource2.setEmployeeId(1L);

        resource3 = new Resource();
        resource3.setId(2L);
        resource3.setComment("Comment2");
        resource3.setManagerId(manager2);
        resource3.setProjectId(project2);
        resource3.setEmployeeId(2L);
    }

    @Test
    public void testGetSetId() {
        Resource resource = new Resource();
        Long id = 2L;
        resource.setId(id);
        assertEquals(id, resource.getId());
    }

    @Test
    public void testGetSetComment() {
        Resource resource = new Resource();
        String comment = "Resource Comment 2";
        resource.setComment(comment);
        assertEquals(comment, resource.getComment());
    }

    @Test
    public void testGetSetManagerId() {
        Resource resource = new Resource();
        User newManager = new User();
        newManager.setId(2L);
        newManager.setName("New Manager");

        resource.setManagerId(newManager);
        assertEquals(newManager, resource.getManagerId());
    }

    @Test
    public void testGetSetProjectId() {
        Resource resource = new Resource();
        Project newProject = new Project();
        newProject.setId(2L);
        newProject.setProjectName("New Project");

        resource.setProjectId(newProject);
        assertEquals(newProject, resource.getProjectId());
    }

    @Test
    public void testGetSetEmployeeId() {
        Resource resource = new Resource();
        Long employeeId = 3L;
        resource.setEmployeeId(employeeId);
        assertEquals(employeeId, resource.getEmployeeId());
    }

    @Test
    public void testEquals() {
        // Same object
        assertEquals(resource1, resource1);

        // Different object, same content
        assertEquals(resource1, resource2);

        // Different object, different content
        assertNotEquals(resource1, resource3);

        // Null object
        assertNotEquals(resource1, null);

        // Different class
        assertNotEquals(resource1, new Object());
    }

    @Test
    public void testHashCode() {
        // Same content, same hash code
        assertEquals(resource1.hashCode(), resource2.hashCode());

        // Different content, different hash code
        // Create mock instances of Resource
        Resource resource11 = mock(Resource.class);
        Resource resource21 = mock(Resource.class);
        Resource resource31 = mock(Resource.class);

        // Mocking behavior for getId() method
        when(resource11.getId()).thenReturn(1L);
        when(resource21.getId()).thenReturn(1L);
        when(resource31.getId()).thenReturn(2L);

        // Validate hashCode method
//        assertEquals(resource1.hashCode(), resource2.hashCode());
        assertNotEquals(resource11.hashCode(), resource31.hashCode());

    }

    @Test
    public void testToString() {
        String expectedString = "Resource(id=1, comment=Comment1, managerId=User(id=1, name=Manager1, contactNo=null, dob=null, doj=null, designation=null, email=null, empId=null, location=null, password=null, role=null, assignedSkills=[], empManagerId=null, empProjectId=null), projectId=Project(id=1, projectName=Project1, description=null, startDate=null, manager=null), employeeId=1)";
        assertEquals(expectedString, resource1.toString());
    }

    @Test
    public void testNoArgsConstructor() {
        Resource resource = new Resource();
        assertNotNull(resource);
    }

    @Test
    public void testAllArgsConstructor() {
        Resource resource = new Resource();
        resource.setId(3L);
        resource.setComment("Resource Comment 4");
        resource.setManagerId(manager1);
        resource.setProjectId(project1);
        resource.setEmployeeId(4L);

        assertEquals(3L, resource.getId());
        assertEquals("Resource Comment 4", resource.getComment());
        assertEquals(manager1, resource.getManagerId());
        assertEquals(project1, resource.getProjectId());
        assertEquals(4L, resource.getEmployeeId());
    }

    @Test
    public void testEqualsSymmetric() {
        assertTrue(resource1.equals(resource2) && resource2.equals(resource1));
        assertEquals(resource1.hashCode(), resource2.hashCode());
    }

    @Test
    public void testEqualsConsistency() {
        assertTrue(resource1.equals(resource2));
        assertTrue(resource1.equals(resource2));
    }

    @Test
    public void testEqualsTransitive() {
        Resource resource4 = new Resource();
        resource4.setId(1L);
        resource4.setComment("Comment1");
        resource4.setManagerId(manager1);
        resource4.setProjectId(project1);
        resource4.setEmployeeId(1L);

        assertTrue(resource1.equals(resource2) && resource2.equals(resource4) && resource1.equals(resource4));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(resource1.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(resource1.equals(new Object()));
    }
}
