package com.employee.Employee.Management.Portal.service;

import com.employee.Employee.Management.Portal.dto.RegisterDto;
import org.junit.jupiter.api.Test;
import com.employee.Employee.Management.Portal.dto.ApiResponseDto;
import com.employee.Employee.Management.Portal.dto.LoginInDto;
import com.employee.Employee.Management.Portal.dto.LoginOutDto;
import com.employee.Employee.Management.Portal.dto.RegisterDto;
import com.employee.Employee.Management.Portal.entity.Role;
import com.employee.Employee.Management.Portal.entity.Skills;
import com.employee.Employee.Management.Portal.entity.User;
import com.employee.Employee.Management.Portal.repository.SkillsRepository;
import com.employee.Employee.Management.Portal.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class RegisterServiceTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private SkillsRepository skillsRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegisterService registerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdminRegister_Success() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail("admin@nucleusteq.com");
        registerDto.setPassword("password");
        registerDto.setName("Admin");
        registerDto.setEmpId("E001");
        registerDto.setDesignation("Admin");
        registerDto.setContactNo("1234567890");
        registerDto.setDob("1990-01-01");
        registerDto.setDoj("2020-01-01");
        registerDto.setLocation("New York");

        when(userRepository.findByRole(Role.ADMIN)).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("hashedPassword");

        ApiResponseDto response = registerService.adminRegister(registerDto);

        assertNotNull(response);
        assertEquals("Admin registered successfully", response.getMessage());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testAdminRegister_UnauthorizedEmail() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail("user@example.com");

        ApiResponseDto response = registerService.adminRegister(registerDto);

        assertNotNull(response);
        assertEquals("You are not authorized to register as admin", response.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testAdminRegister_AdminAlreadyExists() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail("admin@nucleusteq.com");

        when(userRepository.findByRole(Role.ADMIN)).thenReturn(Optional.of(new User()));

        ApiResponseDto response = registerService.adminRegister(registerDto);

        assertNotNull(response);
        assertEquals("Admin already registered", response.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

//    @Test
//    void testUserLogin_Success() {
//        LoginInDto loginInDto = new LoginInDto();
//        loginInDto.setEmail("user@example.com");
//        loginInDto.setPassword("password");
//
//        User user = new User();
//        user.setEmail("user@example.com");
//        user.setPassword("hashedPassword");
//        user.setName("User");
//        user.setRole(Role.EMPLOYEE);
//        user.setId(1L);
//
//        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
//        when(passwordEncoder.matches("password", "hashedPassword")).thenReturn(true);
//
//        LoginOutDto response = registerService.userLogin(loginInDto);
//
//        assertNotNull(response);
//        assertEquals("Login successful", response.getMessage());
//        assertEquals("User", response.getName());
//        assertEquals("user@example.com", response.getEmail());
//        assertEquals(Role.EMPLOYEE, response.getRole());
//        assertEquals(1L, response.getId());
//    }
//
//    @Test
//    void testUserLogin_InvalidCredentials() {
//        LoginInDto loginInDto = new LoginInDto();
//        loginInDto.setEmail("user@example.com");
//        loginInDto.setPassword("wrongPassword");
//
//        User user = new User();
//        user.setEmail("user@example.com");
//        user.setPassword("hashedPassword");
//
//        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
//        when(passwordEncoder.matches("wrongPassword", "hashedPassword")).thenReturn(false);
//
//        LoginOutDto response = registerService.userLogin(loginInDto);
//
//        assertNotNull(response);
//        assertEquals("Invalid credentials", response.getMessage());
//    }
//
//    @Test
//    void testUserLogin_UserNotFound() {
//        LoginInDto loginInDto = new LoginInDto();
//        loginInDto.setEmail("user@example.com");
//        loginInDto.setPassword("password");
//
//        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.empty());
//
//        LoginOutDto response = registerService.userLogin(loginInDto);
//
//        assertNotNull(response);
//        assertEquals("Invalid credentials", response.getMessage());
//    }

    @Test
    void testAddUser_Success() {
        RegisterDto addUserDto = new RegisterDto();
        addUserDto.setEmail("user@nucleusteq.com");
        addUserDto.setPassword("password");
        addUserDto.setName("User");
        addUserDto.setEmpId("E002");
        addUserDto.setDesignation("Developer");
        addUserDto.setContactNo("1234567890");
        addUserDto.setDob("1990-01-01");
        addUserDto.setDoj("2020-01-01");
        addUserDto.setLocation("New York");
        addUserDto.setRole(Role.MANAGER);
        addUserDto.setAssignedSkills(Set.of(1L));

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.empty());
        when(userRepository.findByEmpId("E002")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("hashedPassword");
        when(skillsRepository.findAllById(List.of(1L))).thenReturn(List.of(new Skills()));

        ApiResponseDto response = registerService.addUser(addUserDto);

        assertNotNull(response);
        assertEquals("Employee added successfully", response.getMessage());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testAddUser_EmailAlreadyInUse() {
        RegisterDto addUserDto = new RegisterDto();
        addUserDto.setEmail("user@nucleusteq.com");

        when(userRepository.findByEmail("user@nucleusteq.com")).thenReturn(Optional.of(new User()));

        ApiResponseDto response = registerService.addUser(addUserDto);

        assertNotNull(response);
        assertEquals("Email address already in use", response.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testAddUser_EmpIdAlreadyInUse() {
        RegisterDto addUserDto = new RegisterDto();
        addUserDto.setEmail("user@nucleusteq.com");
        addUserDto.setEmpId("E002");

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.empty());
        when(userRepository.findByEmpId("E002")).thenReturn(Optional.of(new User()));

        ApiResponseDto response = registerService.addUser(addUserDto);

        assertNotNull(response);
        assertEquals("Employee ID already in use", response.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testAddUser_AssignSkills() {
        RegisterDto addUserDto = new RegisterDto();
        addUserDto.setEmail("user@nucleusteq.com");
        addUserDto.setPassword("password");
        addUserDto.setName("User");
        addUserDto.setEmpId("E002");
        addUserDto.setDesignation("Developer");
        addUserDto.setContactNo("1234567890");
        addUserDto.setDob("1990-01-01");
        addUserDto.setDoj("2020-01-01");
        addUserDto.setLocation("New York");
        addUserDto.setRole(Role.EMPLOYEE);
        addUserDto.setAssignedSkills(Set.of(1L));

        when(userRepository.findByEmail("user@nucleusteq.com")).thenReturn(Optional.empty());
        when(userRepository.findByEmpId("E002")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("hashedPassword");
        Skills skill = new Skills();
        skill.setId(1L);
        when(skillsRepository.findAllById(List.of(1L))).thenReturn(List.of(skill));

        ApiResponseDto response = registerService.addUser(addUserDto);

        assertNotNull(response);
        assertEquals("Employee added successfully", response.getMessage());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testUpdateUser_Success() {
        RegisterDto updateUserDto = new RegisterDto();
        updateUserDto.setEmpId("E002");
        updateUserDto.setName("Updated User");
        updateUserDto.setDob("1990-01-01");
        updateUserDto.setDoj("2020-01-01");
        updateUserDto.setEmail("updated.user@example.com");
        updateUserDto.setDesignation("Updated Developer");
        updateUserDto.setContactNo("9876543210");
        updateUserDto.setLocation("Los Angeles");

        User user = new User();
        user.setEmpId("E002");

        when(userRepository.findByEmpId("E002")).thenReturn(Optional.of(user));

        ApiResponseDto response = registerService.updateUser(updateUserDto);

        assertNotNull(response);
        assertEquals("Employee updated successfully", response.getMessage());
        assertEquals("Updated User", user.getName());
        assertEquals("1990-01-01", user.getDob());
        assertEquals("2020-01-01", user.getDoj());
        assertEquals("updated.user@example.com", user.getEmail());
        assertEquals("Updated Developer", user.getDesignation());
        assertEquals("9876543210", user.getContactNo());
        assertEquals("Los Angeles", user.getLocation());
        verify(userRepository).save(user);
    }

    @Test
    void testUpdateUser_NotFound() {
        RegisterDto updateUserDto = new RegisterDto();
        updateUserDto.setEmpId("E002");

        when(userRepository.findByEmpId("E002")).thenReturn(Optional.empty());

        ApiResponseDto response = registerService.updateUser(updateUserDto);

        assertNotNull(response);
        assertEquals("Employee not found", response.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testGetUserDetails() {
        List<User> users = Arrays.asList(new User(), new User());

        when(userRepository.findAll()).thenReturn(users);

        List<User> response = registerService.getUserDetails();

        assertNotNull(response);
        assertEquals(2, response.size());
    }

    @Test
    void testGetAllSkills() {
        List<Skills> skills = Arrays.asList(new Skills(), new Skills());

        when(skillsRepository.findAll()).thenReturn(skills);

        List<Skills> response = registerService.getAllSkills();

        assertNotNull(response);
        assertEquals(2, response.size());
    }
}
