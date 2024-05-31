package com.employee.Employee.Management.Portal.dto;
import com.employee.Employee.Management.Portal.dto.EmpDto;
import com.employee.Employee.Management.Portal.entity.Role;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmpDtoTest {

    @Test
    void testGetterAndSetterMethods() {
        EmpDto empDto = new EmpDto();
        empDto.setId(1L);
        empDto.setName("John Doe");
        empDto.setContactNo("1234567890");
        empDto.setDob("1990-01-01");
        empDto.setDoj("2020-01-01");
        empDto.setDesignation("Software Engineer");
        empDto.setEmail("john.doe@example.com");
        empDto.setEmpId("EMP123");
        empDto.setLocation("New York");
        empDto.setPassword("password");
        empDto.setRole(Role.EMPLOYEE);
        empDto.setEmpManagerId(2L);
        empDto.setEmpProjectId(3L);
        empDto.setManagerName("Manager Name");
        empDto.setProjectName("Project Name");
        Set<String> assignedSkills = new HashSet<>();
        assignedSkills.add("Java");
        assignedSkills.add("Spring");
        empDto.setAssignedSkills(assignedSkills);

        assertEquals(1L, empDto.getId());
        assertEquals("John Doe", empDto.getName());
        assertEquals("1234567890", empDto.getContactNo());
        assertEquals("1990-01-01", empDto.getDob());
        assertEquals("2020-01-01", empDto.getDoj());
        assertEquals("Software Engineer", empDto.getDesignation());
        assertEquals("john.doe@example.com", empDto.getEmail());
        assertEquals("EMP123", empDto.getEmpId());
        assertEquals("New York", empDto.getLocation());
        assertEquals("password", empDto.getPassword());
        assertEquals(Role.EMPLOYEE, empDto.getRole());
        assertEquals(2L, empDto.getEmpManagerId());
        assertEquals(3L, empDto.getEmpProjectId());
        assertEquals("Manager Name", empDto.getManagerName());
        assertEquals("Project Name", empDto.getProjectName());
        assertTrue(empDto.getAssignedSkills().contains("Java"));
        assertTrue(empDto.getAssignedSkills().contains("Spring"));
    }

    @Test
    void testEqualsAndHashCode() {
        EmpDto empDto1 = new EmpDto();
        empDto1.setId(1L);
        empDto1.setName("John Doe");
        empDto1.setContactNo("1234567890");
        empDto1.setDob("1990-01-01");
        empDto1.setDoj("2020-01-01");
        empDto1.setDesignation("Software Engineer");
        empDto1.setEmail("john.doe@example.com");
        empDto1.setEmpId("EMP123");
        empDto1.setLocation("New York");
        empDto1.setPassword("password");
        empDto1.setRole(Role.EMPLOYEE);
        empDto1.setEmpManagerId(2L);
        empDto1.setEmpProjectId(3L);
        empDto1.setManagerName("Manager Name");
        empDto1.setProjectName("Project Name");
        Set<String> assignedSkills1 = new HashSet<>();
        assignedSkills1.add("Java");
        assignedSkills1.add("Spring");
        empDto1.setAssignedSkills(assignedSkills1);

        EmpDto empDto2 = new EmpDto();
        empDto2.setId(1L);
        empDto2.setName("John Doe");
        empDto2.setContactNo("1234567890");
        empDto2.setDob("1990-01-01");
        empDto2.setDoj("2020-01-01");
        empDto2.setDesignation("Software Engineer");
        empDto2.setEmail("john.doe@example.com");
        empDto2.setEmpId("EMP123");
        empDto2.setLocation("New York");
        empDto2.setPassword("password");
        empDto2.setRole(Role.EMPLOYEE);
        empDto2.setEmpManagerId(2L);
        empDto2.setEmpProjectId(3L);
        empDto2.setManagerName("Manager Name");
        empDto2.setProjectName("Project Name");
        Set<String> assignedSkills2 = new HashSet<>();
        assignedSkills2.add("Java");
        assignedSkills2.add("Spring");
        empDto2.setAssignedSkills(assignedSkills2);

        assertTrue(empDto1.equals(empDto2));
        assertEquals(empDto1.hashCode(), empDto2.hashCode());
    }

    @Test
    void testToString() {
        EmpDto empDto = new EmpDto();
        empDto.setId(1L);
        empDto.setName("John Doe");
        empDto.setContactNo("1234567890");
        empDto.setDob("1990-01-01");
        empDto.setDoj("2020-01-01");
        empDto.setDesignation("Software Engineer");
        empDto.setEmail("john.doe@example.com");
        empDto.setEmpId("EMP123");
        empDto.setLocation("New York");
        empDto.setPassword("password");
        empDto.setRole(Role.EMPLOYEE);
        empDto.setEmpManagerId(2L);
        empDto.setEmpProjectId(3L);
        empDto.setManagerName("Manager Name");
        empDto.setProjectName("Project Name");
        Set<String> assignedSkills = new HashSet<>();
        assignedSkills.add("Java");
        assignedSkills.add("Spring");
        empDto.setAssignedSkills(assignedSkills);

        String expectedToString = "EmpDto{id=1, name='John Doe', contactNo='1234567890', dob='1990-01-01', doj='2020-01-01', designation='Software Engineer', email='john.doe@example.com', empId='EMP123', location='New York', password='password', role=EMPLOYEE, EmpManagerId=2, EmpProjectId=3, ManagerName='Manager Name', ProjectName='Project Name', assignedSkills=[Java, Spring]}";
        assertEquals(expectedToString, empDto.toString());
    }

}
