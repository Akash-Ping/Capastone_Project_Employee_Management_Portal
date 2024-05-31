package com.employee.Employee.Management.Portal.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class ManagerOutDtoTest {

    @Test
    public void testGetterSetter() {
        ManagerOutDto managerDto = createManagerDto();
        assertEquals(1L, managerDto.getId());
        assertEquals("John Doe", managerDto.getName());
        assertEquals("john@example.com", managerDto.getEmail());
        assertEquals("1234567890", managerDto.getContactNo());
        assertEquals("Manager", managerDto.getDesignation());
        assertEquals("New York", managerDto.getLocation());
        assertEquals("EMP001", managerDto.getEmpId());
        assertEquals("1990-01-01", managerDto.getDob());
        assertEquals("2020-01-01", managerDto.getDoj());
        assertEquals(123L, managerDto.getEmpProjectId());
        assertEquals(456L, managerDto.getEmpManagerId());
        assertEquals("Manager Name", managerDto.getManagerName());
        assertEquals(Arrays.asList("Project1", "Project2"), managerDto.getProjectName());
        assertEquals(Arrays.asList("Skill1", "Skill2"), managerDto.getAssignedSkills());
    }

    @Test
    public void testEqualsWithSettersAndGetters() {
        ManagerOutDto managerDto1 = createManagerDto();
        ManagerOutDto managerDto2 = createManagerDto();
        ManagerOutDto managerDto3 = createDifferentManagerDto();
        assertTrue(managerDto1.equals(managerDto2));
        assertFalse(managerDto1.equals(managerDto3));
    }

    @Test
    public void testHashCodeWithSettersAndGetters() {
        ManagerOutDto managerDto1 = createManagerDto();
        ManagerOutDto managerDto2 = createManagerDto();
        assertEquals(managerDto1.hashCode(), managerDto2.hashCode());
    }

    private ManagerOutDto createManagerDto() {
        ManagerOutDto managerDto = new ManagerOutDto();
        managerDto.setId(1L);
        managerDto.setName("John Doe");
        managerDto.setEmail("john@example.com");
        managerDto.setContactNo("1234567890");
        managerDto.setDesignation("Manager");
        managerDto.setLocation("New York");
        managerDto.setEmpId("EMP001");
        managerDto.setDob("1990-01-01");
        managerDto.setDoj("2020-01-01");
        managerDto.setEmpProjectId(123L);
        managerDto.setEmpManagerId(456L);
        managerDto.setManagerName("Manager Name");
        managerDto.setProjectName(Arrays.asList("Project1", "Project2"));
        managerDto.setAssignedSkills(Arrays.asList("Skill1", "Skill2"));
        return managerDto;
    }

    private ManagerOutDto createDifferentManagerDto() {
        ManagerOutDto managerDto = new ManagerOutDto();
        managerDto.setId(2L);
        managerDto.setName("Jane Smith");
        managerDto.setEmail("jane@example.com");
        managerDto.setContactNo("9876543210");
        managerDto.setDesignation("Manager");
        managerDto.setLocation("Los Angeles");
        managerDto.setEmpId("EMP002");
        managerDto.setDob("1995-01-01");
        managerDto.setDoj("2022-01-01");
        managerDto.setEmpProjectId(456L);
        managerDto.setEmpManagerId(789L);
        managerDto.setManagerName("Manager Name");
        managerDto.setProjectName(Arrays.asList("Project3", "Project4"));
        managerDto.setAssignedSkills(Arrays.asList("Skill3", "Skill4"));
        return managerDto;
    }
}
