package com.employee.Employee.Management.Portal.dto;

import com.employee.Employee.Management.Portal.entity.Role;
import com.employee.Employee.Management.Portal.entity.Skills;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterOutDtoTest {

    private RegisterOutDto registerOutDto;

    @BeforeEach
    public void setUp() {
        registerOutDto = new RegisterOutDto();
    }

    @Test
    public void testId() {
        registerOutDto.setId(1L);
        assertEquals(1L, registerOutDto.getId());
    }

    @Test
    public void testName() {
        registerOutDto.setName("John Doe");
        assertEquals("John Doe", registerOutDto.getName());
    }

    @Test
    public void testContactNo() {
        registerOutDto.setContactNo("1234567890");
        assertEquals("1234567890", registerOutDto.getContactNo());
    }

    @Test
    public void testDob() {
        registerOutDto.setDob("1990-01-01");
        assertEquals("1990-01-01", registerOutDto.getDob());
    }

    @Test
    public void testDoj() {
        registerOutDto.setDoj("2021-01-01");
        assertEquals("2021-01-01", registerOutDto.getDoj());
    }

    @Test
    public void testDesignation() {
        registerOutDto.setDesignation("Software Engineer");
        assertEquals("Software Engineer", registerOutDto.getDesignation());
    }

    @Test
    public void testEmail() {
        registerOutDto.setEmail("john@example.com");
        assertEquals("john@example.com", registerOutDto.getEmail());
    }

    @Test
    public void testEmpId() {
        registerOutDto.setEmpId("E001");
        assertEquals("E001", registerOutDto.getEmpId());
    }

    @Test
    public void testLocation() {
        registerOutDto.setLocation("New York");
        assertEquals("New York", registerOutDto.getLocation());
    }

    @Test
    public void testRole() {
        registerOutDto.setRole(Role.MANAGER);
        assertEquals(Role.MANAGER, registerOutDto.getRole());
    }

    @Test
    public void testEmpProjectId() {
        registerOutDto.setEmpProjectId(2L);
        assertEquals(2L, registerOutDto.getEmpProjectId());
    }

    @Test
    public void testManagerName() {
        registerOutDto.setManagerName("Jane Smith");
        assertEquals("Jane Smith", registerOutDto.getManagerName());
    }

    @Test
    public void testProjectName() {
        registerOutDto.setProjectName("Project X");
        assertEquals("Project X", registerOutDto.getProjectName());
    }

    @Test
    public void testAssignedSkills() {
        Set<Skills> skills = new HashSet<>();
        Skills skill1 = new Skills();
        skill1.setId(1L);
        skill1.setSkillName("Java");
        Skills skill2 = new Skills();
        skill2.setId(2L);
        skill2.setSkillName("Spring");
        skills.add(skill1);
        skills.add(skill2);

        registerOutDto.setAssignedSkills(skills);
        assertEquals(skills, registerOutDto.getAssignedSkills());
    }

    @Test
    public void testHashCode() {
        RegisterOutDto dto1 = createSampleDto();
        RegisterOutDto dto2 = createSampleDto();
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testEquals() {
        RegisterOutDto dto1 = createSampleDto();
        RegisterOutDto dto2 = createSampleDto();
        assertTrue(dto1.equals(dto2));
        assertFalse(dto1.equals(new Object())); // Ensure equals handles different types
    }

    @Test
    public void testToString() {
        RegisterOutDto dto = createSampleDto();
        String actualString = dto.toString();
        String expectedString = "RegisterOutDto(id=1, name=John Doe, contactNo=1234567890, dob=1990-01-01, doj=2021-01-01, designation=Software Engineer, email=john@example.com, empId=E001, location=New York, role=MANAGER, empProjectId=2, managerName=Jane Smith, projectName=Project X, assignedSkills=[Skills(id=1, skillName=Java), Skills(id=2, skillName=Spring)])";

        System.out.println("Actual String: " + actualString);
        System.out.println("Expected String: " + expectedString);

        assertEquals(expectedString, actualString);
    }

    private RegisterOutDto createSampleDto() {
        RegisterOutDto dto = new RegisterOutDto();
        dto.setId(1L);
        dto.setName("John Doe");
        dto.setContactNo("1234567890");
        dto.setDob("1990-01-01");
        dto.setDoj("2021-01-01");
        dto.setDesignation("Software Engineer");
        dto.setEmail("john@example.com");
        dto.setEmpId("E001");
        dto.setLocation("New York");
        dto.setRole(Role.MANAGER);
        dto.setEmpProjectId(2L);
        dto.setManagerName("Jane Smith");
        dto.setProjectName("Project X");

        Set<Skills> skills = new HashSet<>();
        Skills skill1 = new Skills();
        skill1.setId(1L);
        skill1.setSkillName("Java");
        Skills skill2 = new Skills();
        skill2.setId(2L);
        skill2.setSkillName("Spring");
        skills.add(skill1);
        skills.add(skill2);

        dto.setAssignedSkills(skills);
        return dto;
    }
}
