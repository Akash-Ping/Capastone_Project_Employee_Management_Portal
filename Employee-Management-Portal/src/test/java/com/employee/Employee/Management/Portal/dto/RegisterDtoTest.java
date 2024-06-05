package com.employee.Employee.Management.Portal.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import com.employee.Employee.Management.Portal.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegisterDtoTest {

    private RegisterDto registerDto;

    @BeforeEach
    public void setUp() {
        registerDto = new RegisterDto();
    }

    @Test
    public void testId() {
        registerDto.setId(1L);
        assertEquals(1L, registerDto.getId());
    }

    @Test
    public void testEmpId() {
        registerDto.setEmpId("E001");
        assertEquals("E001", registerDto.getEmpId());
    }

    @Test
    public void testName() {
        registerDto.setName("John Doe");
        assertEquals("John Doe", registerDto.getName());
    }

    @Test
    public void testEmail() {
        registerDto.setEmail("john@example.com");
        assertEquals("john@example.com", registerDto.getEmail());
    }

    @Test
    public void testDob() {
        registerDto.setDob("1990-01-01");
        assertEquals("1990-01-01", registerDto.getDob());
    }

    @Test
    public void testDoj() {
        registerDto.setDoj("2021-01-01");
        assertEquals("2021-01-01", registerDto.getDoj());
    }

    @Test
    public void testLocation() {
        registerDto.setLocation("New York");
        assertEquals("New York", registerDto.getLocation());
    }

    @Test
    public void testDesignation() {
        registerDto.setDesignation("Software Engineer");
        assertEquals("Software Engineer", registerDto.getDesignation());
    }

    @Test
    public void testContactNo() {
        registerDto.setContactNo("1234567890");
        assertEquals("1234567890", registerDto.getContactNo());
    }

    @Test
    public void testPassword() {
        registerDto.setPassword("password123");
        assertEquals("password123", registerDto.getPassword());
    }

    @Test
    public void testRole() {
        Role role = Role.EMPLOYEE;
        registerDto.setRole(role);
        assertEquals(role, registerDto.getRole());
    }

    @Test
    public void testEmpManagerId() {
        registerDto.setEmpManagerId(2L);
        assertEquals(2L, registerDto.getEmpManagerId());
    }

    @Test
    public void testEmpProjectId() {
        registerDto.setEmpProjectId(3L);
        assertEquals(3L, registerDto.getEmpProjectId());
    }

    @Test
    public void testManagerName() {
        registerDto.setManagerName("Jane Smith");
        assertEquals("Jane Smith", registerDto.getManagerName());
    }

    @Test
    public void testProjectName() {
        registerDto.setProjectName("Project X");
        assertEquals("Project X", registerDto.getProjectName());
    }

    @Test
    public void testAssignedSkills() {
        Set<Long> skills = new HashSet<>();
        skills.add(101L);
        skills.add(102L);
        registerDto.setAssignedSkills(skills);
        assertEquals(skills, registerDto.getAssignedSkills());
    }

    @Test
    public void testHashCode() {
        RegisterDto dto1 = createSampleDto();
        RegisterDto dto2 = createSampleDto();
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testEquals() {
        RegisterDto dto1 = createSampleDto();
        RegisterDto dto2 = createSampleDto();
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testToString() {
        RegisterDto dto = createSampleDto();
        String expectedString = "RegisterDto{id=1, name='John Doe', contactNo='1234567890', dob='1990-01-01', doj='2021-01-01', designation='Software Engineer', email='john@example.com', empId='E001', location='New York', password='password123', role=EMPLOYEE, EmpManagerId=null, EmpProjectId=null, ManagerName='Jane Smith', ProjectName='Project X', assignedSkills=[]}";
        assertEquals(expectedString, dto.toString());
    }

    private RegisterDto createSampleDto() {
        RegisterDto dto = new RegisterDto();
        dto.setId(1L);
        dto.setName("John Doe");
        dto.setContactNo("1234567890");
        dto.setDob("1990-01-01");
        dto.setDoj("2021-01-01");
        dto.setDesignation("Software Engineer");
        dto.setEmail("john@example.com");
        dto.setEmpId("E001");
        dto.setLocation("New York");
        dto.setPassword("password123");
        dto.setRole(Role.EMPLOYEE);
        dto.setManagerName("Jane Smith");
        dto.setProjectName("Project X");
        dto.setAssignedSkills(new HashSet<>());
        return dto;
    }
}
