package com.employee.Employee.Management.Portal.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

public class UserDtoTest {

    private UserDto dto1;
    private UserDto dto2;
    private Set<String> assignedSkills1;
    private Set<String> assignedSkills2;

    @BeforeEach
    public void setUp() {
        assignedSkills1 = new HashSet<>();
        assignedSkills1.add("Java");
        assignedSkills1.add("Spring");

        assignedSkills2 = new HashSet<>(assignedSkills1);

        dto1 = new UserDto();
        dto1.setEmpId("EMP001");
        dto1.setName("John Doe");
        dto1.setEmail("john.doe@example.com");
        dto1.setContactNo("1234567890");
        dto1.setDob("1990-01-01");
        dto1.setDoj("2020-01-01");
        dto1.setDesignation("Software Engineer");
        dto1.setLocation("New York");
        dto1.setAssignedSkills(assignedSkills1);
        dto1.setProjectName("Project A");
        dto1.setProjectId(1L);
        dto1.setManagerName("Jane Smith");

        dto2 = new UserDto();
        dto2.setEmpId("EMP001");
        dto2.setName("John Doe");
        dto2.setEmail("john.doe@example.com");
        dto2.setContactNo("1234567890");
        dto2.setDob("1990-01-01");
        dto2.setDoj("2020-01-01");
        dto2.setDesignation("Software Engineer");
        dto2.setLocation("New York");
        dto2.setAssignedSkills(assignedSkills2);
        dto2.setProjectName("Project A");
        dto2.setProjectId(1L);
        dto2.setManagerName("Jane Smith");
    }

    @Test
    public void testGetSetEmpId() {
        UserDto dto = new UserDto();
        String empId = "EMP001";
        dto.setEmpId(empId);
        assertEquals(empId, dto.getEmpId());
    }

    @Test
    public void testGetSetName() {
        UserDto dto = new UserDto();
        String name = "John Doe";
        dto.setName(name);
        assertEquals(name, dto.getName());
    }

    @Test
    public void testGetSetEmail() {
        UserDto dto = new UserDto();
        String email = "john.doe@example.com";
        dto.setEmail(email);
        assertEquals(email, dto.getEmail());
    }

    @Test
    public void testGetSetContactNo() {
        UserDto dto = new UserDto();
        String contactNo = "1234567890";
        dto.setContactNo(contactNo);
        assertEquals(contactNo, dto.getContactNo());
    }

    @Test
    public void testGetSetDob() {
        UserDto dto = new UserDto();
        String dob = "1990-01-01";
        dto.setDob(dob);
        assertEquals(dob, dto.getDob());
    }

    @Test
    public void testGetSetDoj() {
        UserDto dto = new UserDto();
        String doj = "2020-01-01";
        dto.setDoj(doj);
        assertEquals(doj, dto.getDoj());
    }

    @Test
    public void testGetSetDesignation() {
        UserDto dto = new UserDto();
        String designation = "Software Engineer";
        dto.setDesignation(designation);
        assertEquals(designation, dto.getDesignation());
    }

    @Test
    public void testGetSetLocation() {
        UserDto dto = new UserDto();
        String location = "New York";
        dto.setLocation(location);
        assertEquals(location, dto.getLocation());
    }

    @Test
    public void testGetSetAssignedSkills() {
        UserDto dto = new UserDto();
        Set<String> assignedSkills = new HashSet<>();
        assignedSkills.add("Java");
        assignedSkills.add("Spring");
        dto.setAssignedSkills(assignedSkills);
        assertEquals(assignedSkills, dto.getAssignedSkills());
    }

    @Test
    public void testGetSetProjectName() {
        UserDto dto = new UserDto();
        String projectName = "Project A";
        dto.setProjectName(projectName);
        assertEquals(projectName, dto.getProjectName());
    }

    @Test
    public void testGetSetProjectId() {
        UserDto dto = new UserDto();
        Long projectId = 1L;
        dto.setProjectId(projectId);
        assertEquals(projectId, dto.getProjectId());
    }

    @Test
    public void testGetSetManagerName() {
        UserDto dto = new UserDto();
        String managerName = "Jane Smith";
        dto.setManagerName(managerName);
        assertEquals(managerName, dto.getManagerName());
    }

    @Test
    public void testHashCode() {
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testEquals() {
        assertEquals(dto1, dto2);

        UserDto dto3 = new UserDto();
        dto3.setEmpId("EMP002");
        dto3.setName("Jane Doe");
        assertNotEquals(dto1, dto3);
    }

    @Test
    public void testToString() {
        String expectedString = "UserDto(empId=EMP001, name=John Doe, email=john.doe@example.com, contactNo=1234567890, dob=1990-01-01, doj=2020-01-01, designation=Software Engineer, location=New York, assignedSkills=[Java, Spring], projectName=Project A, projectId=1, managerName=Jane Smith)";
        assertEquals(expectedString, dto1.toString());
    }
}
