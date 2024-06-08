package com.employee.Employee.Management.Portal.service;

import com.employee.Employee.Management.Portal.dto.ApiResponseDto;
import com.employee.Employee.Management.Portal.dto.EmpDto;
import com.employee.Employee.Management.Portal.dto.RegisterDto;
import com.employee.Employee.Management.Portal.entity.Project;
import com.employee.Employee.Management.Portal.entity.Skills;
import com.employee.Employee.Management.Portal.entity.User;
import com.employee.Employee.Management.Portal.exception.WrongInputException;
import com.employee.Employee.Management.Portal.repository.ProjectRepository;
import com.employee.Employee.Management.Portal.repository.SkillsRepository;
import com.employee.Employee.Management.Portal.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private SkillsRepository skillsRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEmployee() throws WrongInputException {
        // Set up the user object
        User user = new User();
        user.setId(1L);
        user.setEmpId("E001");
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setContactNo("1234567890");
        user.setDob("1990-01-01");
        user.setDoj("2020-01-01");
        user.setLocation("New York");
        user.setDesignation("Developer");
        Skills javaSkill = new Skills();
        javaSkill.setId(1L);
        javaSkill.setSkillName("Java");
        user.setAssignedSkills(Set.of(javaSkill)); // Correctly assigning Skills
        user.setEmpProjectId(1L);
        user.setEmpManagerId(2L);

        // Set up the project object
        Project project = new Project();
        project.setId(1L);
        project.setProjectName("Project A");
        project.setDescription("Description");

        // Set up the manager object
        User manager = new User();
        manager.setId(2L);
        manager.setName("Manager Name");
        manager.setEmail("manager@example.com");

        // Mock repository calls
        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(userRepository.findById(2L)).thenReturn(Optional.of(manager));

        // Call the method under test
        EmpDto result = employeeService.getEmployee("john.doe@example.com");

        // Assert the result
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("Project A", result.getProjectName());
        assertEquals("Manager Name", result.getManagerName());
    }

    @Test
    void testGetEmployee_ProjectNotFound() {
        User user = new User();
        user.setId(1L);
        user.setEmpId("E001");
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setContactNo("1234567890");
        user.setDob("1990-01-01");
        user.setDoj("2020-01-01");
        user.setLocation("New York");
        user.setDesignation("Developer");
        Skills javaSkill = new Skills();
        javaSkill.setId(1L);
        javaSkill.setSkillName("Java");
        user.setEmpProjectId(1L);
        user.setEmpManagerId(2L);

        // Set up the manager object
        User manager = new User();
        manager.setId(2L);
        manager.setName("Manager Name");
        manager.setEmail("manager@example.com");

        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());
        when(userRepository.findById(2L)).thenReturn(Optional.of(manager));

        WrongInputException exception = assertThrows(WrongInputException.class, () -> {
            employeeService.getEmployee("john.doe@example.com");
        });

        assertEquals("Project not found", exception.getMessage());
    }

    @Test
    void testGetEmployee_ManagerNotFound() {
        // Set up the user object
        User user = new User();
        user.setId(1L);
        user.setEmpId("E001");
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setContactNo("1234567890");
        user.setDob("1990-01-01");
        user.setDoj("2020-01-01");
        user.setLocation("New York");
        user.setDesignation("Developer");
        Skills javaSkill = new Skills();
        javaSkill.setId(1L);
        javaSkill.setSkillName("Java");
        user.setEmpProjectId(1L);
        user.setEmpManagerId(2L);

        // Set up the project object
        Project project = new Project();
        project.setId(1L);
        project.setProjectName("Project A");
        project.setDescription("Description");

        // Mock repository calls
        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        // Assert the exception
        WrongInputException exception = assertThrows(WrongInputException.class, () -> {
            employeeService.getEmployee("john.doe@example.com");
        });
        assertEquals("Manager not found", exception.getMessage());
    }

    @Test
    void testUpdateSkill() {
        User user = new User();
        user.setEmail("abc@nucleusteq.com");
        user.setAssignedSkills(new HashSet<>()); // Use mutable set instead of immutable empty set

        Skills skillToAdd = new Skills();
        skillToAdd.setId(1L);
        skillToAdd.setSkillName("Java");

        Skills skillToRemove = new Skills();
        skillToRemove.setId(2L);
        skillToRemove.setSkillName("Python");

        when(userRepository.findByEmail("abc@nucleusteq.com")).thenReturn(Optional.of(user));
        when(skillsRepository.findById(1L)).thenReturn(Optional.of(skillToAdd));
        when(skillsRepository.findById(2L)).thenReturn(Optional.of(skillToRemove));

        ApiResponseDto response = employeeService.updateSkill("abc@nucleusteq.com", Set.of(1L), Set.of(2L));

        assertNotNull(response);
        assertEquals("User skills updated successfully", response.getMessage());
        verify(userRepository).save(user);
    }

    @Test
    void testUpdateSkill_UserNotFound() {
        when(userRepository.findByEmpId("E001")).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            employeeService.updateSkill("E001", Set.of(1L), Set.of(2L));
        });

        assertEquals("User not found with id: E001", exception.getMessage());
    }

    @Test
    void testUpdateSkill_SkillToAddNotFound() {
        User user = new User();
        user.setEmail("abc@nucleusteq.com");
        user.setAssignedSkills(Collections.emptySet());

        when(userRepository.findByEmail("abc@nucleusteq.com")).thenReturn(Optional.of(user));
        when(skillsRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            employeeService.updateSkill("abc@nucleusteq.com", Set.of(1L), Set.of(2L));
        });

        assertEquals("Skill not found with id: 1", exception.getMessage());
    }

    @Test
    void testUpdateSkill_SkillToRemoveNotFound() {
        // Mock user data
        User user = new User();
        user.setEmail("abc@nucleusteq.com");
        user.setAssignedSkills(Collections.emptySet());

        // Mock skill to add
        Skills skillToAdd = new Skills();
        skillToAdd.setId(1L);
        skillToAdd.setSkillName("Java");

        // Mock UserRepository behavior
        when(userRepository.findByEmail("abc@nucleusteq.com")).thenReturn(Optional.of(user));

        // Mock skill to remove not found in repository
        when(skillsRepository.findById(1L)).thenReturn(Optional.of(skillToAdd));
        when(skillsRepository.findById(2L)).thenReturn(Optional.empty());

        // Call the method under test and assert the exception
        Throwable exception = assertThrows(UnsupportedOperationException.class, () -> {
            employeeService.updateSkill("abc@nucleusteq.com", Set.of(1L), Set.of(2L));
        });

        // Verify that the exception message is correct
        assertNull(exception.getMessage());
    }

    @Test
    void testUpdatePassword() {
        User user = new User();
        user.setEmail("john.doe@example.com");

        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");

        RegisterDto registerDto = new RegisterDto();
        registerDto.setPassword("newPassword");

        ApiResponseDto response = employeeService.updatePassword("john.doe@example.com", registerDto);

        assertNotNull(response);
        assertEquals("Password updated successfully", response.getMessage());
        verify(userRepository).save(user);
    }

    @Test
    void testUpdatePassword_UserNotFound() {
        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.empty());

        RegisterDto registerDto = new RegisterDto();
        registerDto.setPassword("newPassword");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            employeeService.updatePassword("john.doe@example.com", registerDto);
        });

        assertEquals("User not found with email: john.doe@example.com", exception.getMessage());
    }
}
