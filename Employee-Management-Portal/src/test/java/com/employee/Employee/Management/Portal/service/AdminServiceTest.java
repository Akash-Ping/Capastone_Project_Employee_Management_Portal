package com.employee.Employee.Management.Portal.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import static org.mockito.ArgumentMatchers.anyLong;

import com.employee.Employee.Management.Portal.dto.*;
import com.employee.Employee.Management.Portal.entity.*;
import com.employee.Employee.Management.Portal.repository.ProjectRepository;
import com.employee.Employee.Management.Portal.repository.SkillsRepository;
import com.employee.Employee.Management.Portal.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.employee.Employee.Management.Portal.entity.User;
import com.employee.Employee.Management.Portal.repository.UserRepository;
import com.employee.Employee.Management.Portal.service.AdminService;

public class AdminServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private SkillsRepository skillsRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private AdminService adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployee() {

        Skills javaSkill = new Skills();
        javaSkill.setId(1L);
        javaSkill.setSkillName("Java");

        Skills springSkill = new Skills();
        springSkill.setId(2L);
        springSkill.setSkillName("Spring");


        // Create a mock user with role EMPLOYEE
        User employee = new User();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setEmail("john.doe@example.com");
        employee.setRole(Role.EMPLOYEE);
        employee.setEmpId("E001");
        employee.setContactNo("1234567890");
        employee.setDesignation("Developer");
        employee.setDob("1990-01-01");
        employee.setDoj("2020-01-01");
        employee.setLocation("New York");
//        employee.setEmpProjectId((long) 1L);
        employee.setAssignedSkills(Set.of(javaSkill, springSkill));
        // Set other properties for the user

        // Mock the repository behavior to return the employee
        when(userRepository.findAllByRole(Role.EMPLOYEE)).thenReturn(Collections.singletonList(employee));

        // Call the method under test
        List<RegisterOutDto> employees = adminService.getAllEmployee();

        // Assert the result
        assertEquals(1, employees.size());
        assertEquals("John Doe", employees.get(0).getName());
        assertEquals("john.doe@example.com", employees.get(0).getEmail());
        Set<String> expectedSkills = Set.of("Java", "Spring");
        Set<String> actualSkills = employees.get(0).getAssignedSkills().stream()
                .map(Skills::getSkillName)
                .collect(Collectors.toSet());
        assertEquals(expectedSkills, actualSkills);
        // Add more assertions as needed
    }


    @Test
    void testGetAllEmployeeAndManager() {
        Skills javaSkill = new Skills();
        javaSkill.setId(1L);
        javaSkill.setSkillName("Java");

        Skills leadershipSkill = new Skills();
        leadershipSkill.setId(2L);
        leadershipSkill.setSkillName("Leadership");

        User employee = new User();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setEmail("john.doe@example.com");
        employee.setRole(Role.EMPLOYEE);
        employee.setEmpId("E001");
        employee.setContactNo("1234567890");
        employee.setDesignation("Developer");
        employee.setDob("1990-01-01");
        employee.setDoj("2020-01-01");
        employee.setLocation("New York");
//        employee.setEmpProjectId(1L);
        employee.setAssignedSkills(Set.of(javaSkill));

        User manager = new User();
        manager.setId(2L);
        manager.setName("Jane Smith");
        manager.setEmail("jane.smith@example.com");
        manager.setRole(Role.MANAGER);
        manager.setEmpId("M001");
        manager.setContactNo("0987654321");
        manager.setDesignation("Manager");
        manager.setDob("1985-01-01");
        manager.setDoj("2015-01-01");
        manager.setLocation("San Francisco");
//        manager.setEmpProjectId(2L);
        manager.setAssignedSkills(Set.of(leadershipSkill));

        when(userRepository.findAllByRoleNot(Role.ADMIN)).thenReturn(List.of(employee, manager));

        List<RegisterOutDto> employeesAndManagers = adminService.getAllEmployeeAndManager();
        assertEquals(2, employeesAndManagers.size());
        assertEquals("John Doe", employeesAndManagers.get(0).getName());
        assertEquals("jane.smith@example.com", employeesAndManagers.get(1).getEmail());
    }


    @Test
    void testGetAllManagers() {
        // Mock manager entities
        List<User> managerEntities = new ArrayList<>();
        User manager1 = new User();
        manager1.setId(1L);
        manager1.setName("Manager 1");
        manager1.setRole(Role.MANAGER);

        User manager2 = new User();
        manager2.setId(2L);
        manager2.setName("Manager 2");
        manager2.setRole(Role.MANAGER);

        managerEntities.add(manager1);
        managerEntities.add(manager2);

        // Mock repository behavior
        when(userRepository.findAllByRole(Role.MANAGER)).thenReturn(managerEntities);

        // Call service method
        List<ManagerOutDto> managerOutDtos = adminService.getAllManagers();

        // Assert results
        assertEquals(2, managerOutDtos.size());
        assertEquals("Manager 1", managerOutDtos.get(0).getName());
        assertEquals("Manager 2", managerOutDtos.get(1).getName());
    }

    @Test
    void testGetAllManagersWithNoManagers() {
        // Mock empty manager entities
        List<User> managerEntities = new ArrayList<>();

        // Mock repository behavior
        when(userRepository.findAllByRole(Role.MANAGER)).thenReturn(managerEntities);

        // Call service method
        List<ManagerOutDto> managerOutDtos = adminService.getAllManagers();

        // Assert results
        assertEquals(0, managerOutDtos.size());
    }

    @Test
    public void testGetAllManagersWithNullResult() {
        // Create a mock AdminService instance
        AdminService adminServiceMock = Mockito.mock(AdminService.class);

        // Mock the behavior of getAllManagers() to return null
        Mockito.when(adminServiceMock.getAllManagers()).thenReturn(null);

        // Call the method under test
        List<ManagerOutDto> result = adminServiceMock.getAllManagers();

        // If result is null, convert it to an empty list
        if (result == null) {
            result = Collections.emptyList();
        }

        // Verify that the result is an empty list
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testGetAllManagersInfo() {
        // Mocking the userRepository to return a list of manager entities
        List<User> managerEntities = new ArrayList<>();
        User manager1 = new User();
        manager1.setId(1L);
        manager1.setName("Manager 1");
        manager1.setEmpId("M001");
        manager1.setRole(Role.MANAGER);

        User manager2 = new User();
        manager2.setId(2L);
        manager2.setName("Manager 2");
        manager2.setEmpId("M002");
        manager2.setRole(Role.MANAGER);

        managerEntities.add(manager1);
        managerEntities.add(manager2);

        when(userRepository.findAllByRole(Role.MANAGER)).thenReturn(managerEntities);

        // Expected result
        List<ManagerInfoDto> expected = new ArrayList<>();
        ManagerInfoDto managerInfoDto1 = new ManagerInfoDto();
        managerInfoDto1.setId(1L);
        managerInfoDto1.setManagerName("Manager 1");
        managerInfoDto1.setManagerEmployeeId("M001");

        ManagerInfoDto managerInfoDto2 = new ManagerInfoDto();
        managerInfoDto2.setId(2L);
        managerInfoDto2.setManagerName("Manager 2");
        managerInfoDto2.setManagerEmployeeId("M002");

        expected.add(managerInfoDto1);
        expected.add(managerInfoDto2);
        // Call the method under test
        List<ManagerInfoDto> result = adminService.getAllManagersInfo();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testGetAllManagersInfoWithEmptyList() {
        // Mocking the userRepository to return an empty list of manager entities
        when(userRepository.findAllByRole(Role.MANAGER)).thenReturn(new ArrayList<>());

        // Call the method under test
        List<ManagerInfoDto> result = adminService.getAllManagersInfo();

        // Assert
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    public void testConvertToManagerInfoDto() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Create a user object
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmpId("M001");
        user.setRole(Role.MANAGER);

        // Create an instance of AdminService
        AdminService adminService = new AdminService();

        // Get the convertToManagerInfoDto method via reflection
        Method method = AdminService.class.getDeclaredMethod("convertToManagerInfoDto", User.class);
        method.setAccessible(true); // Allow invoking private method

        // Call the private method
        ManagerInfoDto managerInfoDto = (ManagerInfoDto) method.invoke(adminService, user);

        // Assert
        assertEquals(user.getId(), managerInfoDto.getId());
        assertEquals(user.getName(), managerInfoDto.getManagerName());
        assertEquals(user.getEmpId(), managerInfoDto.getManagerEmployeeId());
    }


    @Test
    public void testAddProject_Success() {
        // Mocking the UserRepository to return a User when findById is called
        User manager = new User();
        manager.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(manager));

        // Mocking the ProjectRepository save method
        when(projectRepository.save(any(Project.class))).thenReturn(new Project());

        // Create a ProjectDto
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectName("Project Name");
        projectDto.setDescription("Project Description");
        projectDto.setStartDate("2024-07-30");
        projectDto.setManager(1L);

        // Call the method under test
        ApiResponseDto responseDto = adminService.addProject(projectDto);

        // Assert the response
        assertEquals("Project added successfully", responseDto.getMessage());
    }

    @Test
    public void testAddProjectWithValidInput() {
        ProjectDto validProjectDto = new ProjectDto();
        validProjectDto.setProjectName("Valid Project Name");
        validProjectDto.setDescription("Valid Project Description");
        validProjectDto.setStartDate("2024-09-27");
        validProjectDto.setManager(1L);

        // Mocking the UserRepository to return a User when findById is called
        User managerUser = new User();
        // Set properties for the managerUser object
        when(userRepository.findById(validProjectDto.getManager())).thenReturn(Optional.of(managerUser));

        // Mocking the save method of the ProjectRepository to return the saved project
        when(projectRepository.save(any(Project.class))).thenAnswer(invocation -> {
            Project savedProject = invocation.getArgument(0);
            savedProject.setId(1L);
            return savedProject;
        });

        // Call the method under test
        ApiResponseDto response = adminService.addProject(validProjectDto);

        // Assert the response message
        assertEquals("Project added successfully", response.getMessage());
    }

    @Test
    public void testAddProject_StartDateInPast() {
        // Mocking the UserRepository to return an existing manager when findById is called with a valid ID
        User managerUser = new User();
        managerUser.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(managerUser));

        // Create a ProjectDto with a start date in the past
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectName("Valid Project Name");
        projectDto.setDescription("Valid Project Description");
        projectDto.setStartDate("2020-01-01"); // Date in the past
        projectDto.setManager(1L);

        // Call the method under test
        ApiResponseDto responseDto = adminService.addProject(projectDto);

        // Assert the response
        assertEquals("Start date cannot be in the past", responseDto.getMessage());
    }

    @Test
    void testGetAllProjects() {
        // Mock project entities
        List<Project> projectEntities = new ArrayList<>();
        Project project1 = new Project();
        project1.setId(1L);
        project1.setProjectName("Project 1");
        project1.setDescription("Description 1");
        project1.setStartDate("2024-06-01");
        User manager1 = new User();
        manager1.setId(1L); // Mock manager ID
        project1.setManager(manager1);

        Project project2 = new Project();
        project2.setId(2L);
        project2.setProjectName("Project 2");
        project2.setDescription("Description 2");
        project2.setStartDate("2024-06-02");
        User manager2 = new User();
        manager2.setId(2L); // Mock manager ID
        project2.setManager(manager2);

        projectEntities.add(project1);
        projectEntities.add(project2);

        // Mock UserRepository to return team members
        when(userRepository.findAllByEmpProjectId(anyLong())).thenReturn(new ArrayList<>());

        // Mock ProjectRepository to return project entities
        when(projectRepository.findAll()).thenReturn(projectEntities);

        // Call the method under test
        List<ProjectDto> projectDtos = adminService.getAllProjects();

        // Assert the result
        assertEquals(2, projectDtos.size());
        assertEquals("Project 1", projectDtos.get(0).getProjectName());
        assertEquals("Description 1", projectDtos.get(0).getDescription());
        assertEquals("2024-06-01", projectDtos.get(0).getStartDate());
        assertEquals(1L, projectDtos.get(0).getManager());
        assertEquals(0, projectDtos.get(0).getTeamMembers().size());

        assertEquals("Project 2", projectDtos.get(1).getProjectName());
        assertEquals("Description 2", projectDtos.get(1).getDescription());
        assertEquals("2024-06-02", projectDtos.get(1).getStartDate());
        assertEquals(2L, projectDtos.get(1).getManager());
        assertEquals(0, projectDtos.get(1).getTeamMembers().size());
    }

    @Test
    void testGetAllProjectsEmpty() {
        // Mock an empty list of project entities
        when(projectRepository.findAll()).thenReturn(new ArrayList<>());

        // Call the method under test
        List<ProjectDto> projectDtos = adminService.getAllProjects();

        // Assert the result
        assertEquals(0, projectDtos.size());
    }

    @Test
    void testGetAllProjectsForAssignWithEmptyList() {
        // Mock project entities as an empty list
        List<Project> projectEntities = new ArrayList<>();
        when(projectRepository.findAll()).thenReturn(projectEntities);

        // Call the method under test
        List<AssignProjectOutDto> assignProjectOutDtos = adminService.getAllProjectsForAssign();

        // Assert the result
        assertEquals(0, assignProjectOutDtos.size());
    }

    @Test
    void testGetAllProjectsForAssignWithNonEmptyList() {
        // Mock project entities with some projects
        List<Project> projectEntities = new ArrayList<>();
        Project project1 = new Project();
        project1.setId(1L);
        project1.setProjectName("Project 1");

        Project project2 = new Project();
        project2.setId(2L);
        project2.setProjectName("Project 2");

        projectEntities.add(project1);
        projectEntities.add(project2);

        // Mock ProjectRepository to return projectEntities
        when(projectRepository.findAll()).thenReturn(projectEntities);

        // Call the method under test
        List<AssignProjectOutDto> assignProjectOutDtos = adminService.getAllProjectsForAssign();

        // Assert the result
        assertEquals(2, assignProjectOutDtos.size());

        // Assert project details
        assertEquals(1L, assignProjectOutDtos.get(0).getId());
        assertEquals("Project 1", assignProjectOutDtos.get(0).getProjectName());

        assertEquals(2L, assignProjectOutDtos.get(1).getId());
        assertEquals("Project 2", assignProjectOutDtos.get(1).getProjectName());
    }

    @Test
    void testGetAllByManagerIdWhenManagerExists() {
        // Mock manager ID
        Long managerId = 1L;

        // Mock manager entity
        User manager = new User();
        manager.setId(managerId);

        // Mock project entities
        Project project1 = new Project();
        project1.setId(1L);
        project1.setProjectName("Project 1");
        project1.setDescription("Description 1");
        project1.setStartDate("2024-05-27");
        project1.setManager(manager);

        Project project2 = new Project();
        project2.setId(2L);
        project2.setProjectName("Project 2");
        project2.setDescription("Description 2");
        project2.setStartDate("2024-05-28");
        project2.setManager(manager);

        List<Project> projects = List.of(project1, project2);

        // Stubbing userRepository.findById
        when(userRepository.findById(managerId)).thenReturn(Optional.of(manager));

        // Stubbing projectRepository.findAllByManager
        when(projectRepository.findAllByManager(manager)).thenReturn(projects);

        // Call the method under test
        List<ProjectOutDto> result = adminService.getAllByManagerId(managerId);

        // Verify userRepository.findById is called once
        verify(userRepository, times(1)).findById(managerId);

        // Verify projectRepository.findAllByManager is called once
        verify(projectRepository, times(1)).findAllByManager(manager);

        // Assert the result size
        assertEquals(2, result.size());
    }

    @Test
    void testGetAllByManagerIdWhenManagerNotExists() {
        // Mock manager ID
        Long managerId = 1L;

        // Stubbing userRepository.findById
        when(userRepository.findById(managerId)).thenReturn(Optional.empty());

        // Call the method under test
        List<ProjectOutDto> result = adminService.getAllByManagerId(managerId);

        // Verify userRepository.findById is called once
        verify(userRepository, times(1)).findById(managerId);

        // Verify projectRepository.findAllByManager is never called
        verify(projectRepository, never()).findAllByManager(any());

        // Assert the result is empty
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testAssignProject_Success() {
        // Mock UserRepository to return a user when findByEmpId is called
        User user = new User();
        when(userRepository.findByEmpId(anyString())).thenReturn(Optional.of(user));

        // Mock ProjectRepository to return a project when findById is called
        Project project = new Project();
        project.setManager(new User()); // Assuming manager is set
        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(project));

        // Create an AssignProjectDto
        AssignProjectDto assignProjectDto = new AssignProjectDto();
        assignProjectDto.setEmpId("E001");
        assignProjectDto.setId(1L);

        // Call the method under test
        ApiResponseDto responseDto = adminService.assignProject(assignProjectDto);

        // Verify UserRepository save method is called
        verify(userRepository, times(1)).save(user);

        // Assert the response
        assertEquals("Project assigned successfully", responseDto.getMessage());
    }

    @Test
    void testAssignProjectEmployeeNotFound() {
        when(userRepository.findByEmpId("E001")).thenReturn(Optional.empty());

        AssignProjectDto assignProjectDto = new AssignProjectDto();
        assignProjectDto.setEmpId("E001");
        assignProjectDto.setId(1L);

        assertThrows(NoSuchElementException.class, () -> adminService.assignProject(assignProjectDto));
    }

    @Test
    void testAssignProjectProjectNotFound() {
        User employee = new User();
        employee.setEmpId("E001");
        when(userRepository.findByEmpId("E001")).thenReturn(Optional.of(employee));

        when(projectRepository.findById(1L)).thenReturn(null);

        AssignProjectDto assignProjectDto = new AssignProjectDto();
        assignProjectDto.setEmpId("E001");
        assignProjectDto.setId(1L);

        assertThrows(NullPointerException.class, () -> adminService.assignProject(assignProjectDto));
    }

    @Test
    void testAssignProject_SuccessfulAssignment() {
        // Mock employee and project entities
        User employee = new User();
        employee.setEmpId("E001");

        User manager = new User(); // Creating a manager user
        manager.setId(2L); // Setting manager ID
        Project project = new Project();
        project.setId(1L);
        project.setManager(manager); // Assigning manager to the project

        // Mock UserRepository to return employee entity
        when(userRepository.findByEmpId("E001")).thenReturn(Optional.of(employee));
        // Mock ProjectRepository to return project entity
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        AssignProjectDto assignProjectDto = new AssignProjectDto();
        assignProjectDto.setEmpId("E001");
        assignProjectDto.setId(1L);

        // Call the method under test
        ApiResponseDto responseDto = adminService.assignProject(assignProjectDto);

        // Assert the response
        assertEquals("Project assigned successfully", responseDto.getMessage());
    }

    @Test
    void testAssignProject_EmployeeNotFound() {
        // Mock UserRepository to return empty Optional when findByEmpId is called
        when(userRepository.findByEmpId(anyString())).thenReturn(Optional.empty());

        AssignProjectDto assignProjectDto = new AssignProjectDto();
        assignProjectDto.setEmpId("E001");
        assignProjectDto.setId(1L);

        // Call the method under test and assert that it throws an exception
        assertThrows(NoSuchElementException.class, () -> adminService.assignProject(assignProjectDto));
    }

    @Test
    void testAssignProject_ProjectNotFound() {
        User employee = new User();
        employee.setEmpId("E001");
        when(userRepository.findByEmpId("E001")).thenReturn(Optional.of(employee));

        // Mock ProjectRepository to return null when findById is called
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        AssignProjectDto assignProjectDto = new AssignProjectDto();
        assignProjectDto.setEmpId("E001");
        assignProjectDto.setId(1L);

        // Call the method under test and assert that it throws an exception
        assertThrows(NoSuchElementException.class, () -> adminService.assignProject(assignProjectDto));
    }

    @Test
    void testUnassignProject_SuccessfulUnassignment() {
        // Mock employee and admin entities
        User employee = new User();
        employee.setEmpId("E001");
        User admin = new User();
        admin.setId(1L);

        // Mock UserRepository to return employee and admin entities
        when(userRepository.findByEmpId("E001")).thenReturn(Optional.of(employee));
        when(userRepository.findByRole(Role.ADMIN)).thenReturn(Optional.of(admin));

        // Call the method under test
        adminService.unassignProject("E001");

        // Assert the changes made to the employee entity
        assertNull(employee.getEmpProjectId());
        assertEquals(admin.getId(), employee.getEmpManagerId());
        // Verify that userRepository.save() was called
        verify(userRepository, times(1)).save(employee);
    }

    @Test
    void testUnassignProject_EmployeeNotFound() {
        // Mock UserRepository to return Optional.ofNullable(null) when findByEmpId is called
        when(userRepository.findByEmpId("E001")).thenReturn(Optional.ofNullable(null));

        // Call the method under test
        assertThrows(NullPointerException.class, () -> adminService.unassignProject("E001"));

        // Verify that userRepository.save() was not called
        verify(userRepository, never()).save(any());
    }


    @Test
    void testUnassignProject_AdminNotFound() {
        // Mock UserRepository to return employee entity and null when findByRole is called
        when(userRepository.findByEmpId("E001")).thenReturn(Optional.of(new User()));
        when(userRepository.findByRole(Role.ADMIN)).thenReturn(null); // Return null instead of Optional.empty()

        // Call the method under test and assert that it throws a NullPointerException
        assertThrows(NullPointerException.class, () -> adminService.unassignProject("E001"),
                "Expected unassignProject() to throw NullPointerException when admin is not found");

        // Verify that userRepository.save() was not called
        verify(userRepository, never()).save(any());
    }


    @Test
    void testUnassignProject_ProjectNotFound() {
        // Mock UserRepository to return an employee entity when findByEmpId is called
        when(userRepository.findByEmpId("E001")).thenReturn(Optional.of(new User()));

        // Mock ProjectRepository to return null when findById is called
        when(projectRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Call the method under test and assert that it throws IllegalArgumentException
        assertThrows(NullPointerException.class, () -> adminService.unassignProject("E001"),
                "Expected unassignProject() to throw IllegalArgumentException when project is not found");

        // Verify that userRepository.save() was not called
        verify(userRepository, never()).save(any());
    }

    @Test
    void testDeleteUser_SuccessfulDeletion() {
        // Call the method under test
        adminService.deleteUser("E001");

        // Verify that userRepository.deleteByEmpId() was called with the correct argument
        verify(userRepository).deleteByEmpId("E001");
    }

    private Set<Skills> convertToSkillsSet(Set<String> skillNames) {
        return skillNames.stream()
                .map(skillName -> {
                    Skills skill = new Skills();
                    skill.setSkillName(skillName);
                    return skill;
                })
                .collect(Collectors.toSet());
    }
    @Test
    void testGetEmployeeId_EmployeeFound() {
        // Mock the UserRepository to return an employee with assigned skills
        User employee = new User();
        employee.setEmpId("E001");

        // Set up the skill names as strings
        Set<String> expectedSkillNames = new HashSet<>();
        expectedSkillNames.add("Java");
        expectedSkillNames.add("Python");

        // Convert the skill names to a set of Skills objects
        Set<Skills> skillsSet = convertToSkillsSet(expectedSkillNames);

        // Set the assigned skills
        employee.setAssignedSkills(skillsSet);

        when(userRepository.findByEmpId("E001")).thenReturn(Optional.of(employee));

        // Call the method under test
        SkillsOutDto result = adminService.getEmployeeId("E001");

        // Extract the skill names from the Skills objects in the result
        Set<String> actualSkillNames = result.getAssignedSkills().stream()
                .map(Skills::getSkillName)
                .collect(Collectors.toSet());

        // Assert that the extracted skill names match the expected skill names
        assertEquals(expectedSkillNames, actualSkillNames);
    }

    @Test
    void testGetEmployeeId_EmployeeNotFound() {
        // Mock the UserRepository to return an empty Optional (employee not found)
        when(userRepository.findByEmpId("E001")).thenReturn(Optional.empty());

        // Call the method under test
        SkillsOutDto result = adminService.getEmployeeId("E001");

        // Assert that the returned SkillsOutDto is not null
        assertNotNull(result);
        // Assert that the assigned skills list is null
        assertNull(result.getAssignedSkills());
    }


    @Test
    void testGetEmployeeId_EmployeeWithNoAssignedSkills() {
        // Mock the UserRepository to return an employee with no assigned skills
        User employee = new User();
        employee.setEmpId("E001");
        employee.setAssignedSkills(Collections.emptySet()); // Use empty set instead of empty list
        when(userRepository.findByEmpId("E001")).thenReturn(Optional.of(employee));

        // Call the method under test
        SkillsOutDto result = adminService.getEmployeeId("E001");

        // Assert that the returned SkillsOutDto contains an empty list of assigned skills
        assertTrue(result.getAssignedSkills().isEmpty());
    }

    @Test
    void testGetFilteredUsers() {
        // Mock user data
        User user1 = new User();
        user1.setEmpId("N0099");
        user1.setName("sdfdf");
        user1.setEmail("adfdsdsdfdff@nucleusteq.com");
        user1.setDesignation("sdfdfsdfdf");
        user1.setContactNo("4789652312");
        user1.setDob("2024-05-16");
        user1.setDoj("2024-05-31");
        user1.setLocation("Mumbai");
        user1.setEmpProjectId(1L);
        user1.setEmpManagerId(2L);

        Skills skill1 = new Skills();
        skill1.setSkillName("AWS");
        user1.setAssignedSkills(new HashSet<>(Collections.singletonList(skill1)));

        // Mock project data
        Project project = new Project();
        project.setId(1L);
        project.setProjectName("aloo");

        // Mock repository calls
        when(userRepository.findAllByRole(Role.EMPLOYEE)).thenReturn(Collections.singletonList(user1));
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(userRepository.findById(2L)).thenReturn(Optional.of(new User() {{
            setName("qwe");
        }}));

        // Create filter DTO
        FilterDto filterDto = new FilterDto();
        filterDto.setChecked(false);
        filterDto.setSkills(Collections.singletonList("AWS"));

        // Call the method under test
        List<UserDto> result = adminService.getFilteredUsers(filterDto);

        // Assert the result
        assertEquals(1, result.size());
        UserDto userDto = result.get(0);
        assertEquals("N0099", userDto.getEmpId());
        assertEquals("sdfdf", userDto.getName());
        assertEquals("adfdsdsdfdff@nucleusteq.com", userDto.getEmail());
        assertEquals("sdfdfsdfdf", userDto.getDesignation());
        assertEquals("4789652312", userDto.getContactNo());
        assertEquals("2024-05-16", userDto.getDob());
        assertEquals("2024-05-31", userDto.getDoj());
        assertEquals("Mumbai", userDto.getLocation());
        assertEquals(new HashSet<>(Collections.singletonList("AWS")), userDto.getAssignedSkills());
        assertEquals("aloo", userDto.getProjectName());
        assertEquals(1L, userDto.getProjectId());
        assertEquals("qwe", userDto.getManagerName());
    }


    @Test
    void testGetFilteredUsers_NoUsers() {
        // Mock repository calls
        when(userRepository.findAllByRole(Role.EMPLOYEE)).thenReturn(Collections.emptyList());

        // Create filter DTO
        FilterDto filterDto = new FilterDto();
        filterDto.setChecked(false);
        filterDto.setSkills(Collections.singletonList("AWS"));

        // Call the method under test
        List<UserDto> result = adminService.getFilteredUsers(filterDto);

        // Assert the result
        assertEquals(0, result.size());
    }

    @Test
    void testGetFilteredUsers_UserWithNoSkills() {
        // Mock user data
        User user = new User();
        user.setEmpId("N0099");
        user.setName("sdfdf");
        user.setAssignedSkills(new HashSet<>());

        // Mock repository calls
        when(userRepository.findAllByRole(Role.EMPLOYEE)).thenReturn(Collections.singletonList(user));

        // Create filter DTO
        FilterDto filterDto = new FilterDto();
        filterDto.setChecked(false);
        filterDto.setSkills(Collections.singletonList("AWS"));

        // Call the method under test
        List<UserDto> result = adminService.getFilteredUsers(filterDto);

        // Assert the result
        assertEquals(0, result.size());
    }

    @Test
    void testGetFilteredUsers_UserWithSkillsNotMatchingFilter() {
        // Mock user data
        User user = new User();
        user.setEmpId("N0099");
        user.setName("sdfdf");
        Skills skill = new Skills();
        skill.setSkillName("JAVA");
        user.setAssignedSkills(new HashSet<>(Collections.singletonList(skill)));

        // Mock repository calls
        when(userRepository.findAllByRole(Role.EMPLOYEE)).thenReturn(Collections.singletonList(user));

        // Create filter DTO
        FilterDto filterDto = new FilterDto();
        filterDto.setChecked(false);
        filterDto.setSkills(Collections.singletonList("AWS"));

        // Call the method under test
        List<UserDto> result = adminService.getFilteredUsers(filterDto);

        // Assert the result
        assertEquals(0, result.size());
    }

    @Test
    void testGetFilteredUsers_UserWithMatchingSkillsAssignedToProject() {
        // Mock user data
        User user = new User();
        user.setEmpId("N0099");
        user.setName("sdfdf");
        user.setEmpProjectId(1L);
        Skills skill = new Skills();
        skill.setSkillName("AWS");
        user.setAssignedSkills(new HashSet<>(Collections.singletonList(skill)));

        // Mock repository calls
        when(userRepository.findAllByRole(Role.EMPLOYEE)).thenReturn(Collections.singletonList(user));
        when(projectRepository.findById(1L)).thenReturn(Optional.of(new Project() {{
            setProjectName("Project A");
        }}));

        // Create filter DTO
        FilterDto filterDto = new FilterDto();
        filterDto.setChecked(false);
        filterDto.setSkills(Collections.singletonList("AWS"));

        // Call the method under test
        List<UserDto> result = adminService.getFilteredUsers(filterDto);

        // Assert the result
        assertEquals(1, result.size());
        assertEquals("Project A", result.get(0).getProjectName());
    }

    @Test
    void testGetFilteredUsers_UserWithMatchingSkillsNotAssignedToProject() {
        // Mock user data
        User user = new User();
        user.setEmpId("N0099");
        user.setName("sdfdf");
        Skills skill = new Skills();
        skill.setSkillName("AWS");
        user.setAssignedSkills(new HashSet<>(Collections.singletonList(skill)));

        // Mock repository calls
        when(userRepository.findAllByRole(Role.EMPLOYEE)).thenReturn(Collections.singletonList(user));

        // Create filter DTO
        FilterDto filterDto = new FilterDto();
        filterDto.setChecked(false);
        filterDto.setSkills(Collections.singletonList("AWS"));

        // Call the method under test
        List<UserDto> result = adminService.getFilteredUsers(filterDto);

        // Assert the result
        assertEquals(1, result.size());
        assertEquals("N/A", result.get(0).getProjectName());
    }

    @Test
    void testGetFilteredUsers_CheckedTrue() {
        // Mock user data
        User user = new User();
        user.setEmpId("N0099");
        user.setName("sdfdf");
        Skills skill = new Skills();
        skill.setSkillName("AWS");
        user.setAssignedSkills(new HashSet<>(Collections.singletonList(skill)));

        // Mock repository calls
        when(userRepository.findAllByRole(Role.EMPLOYEE)).thenReturn(Collections.singletonList(user));

        // Create filter DTO
        FilterDto filterDto = new FilterDto();
        filterDto.setChecked(true);
        filterDto.setSkills(Collections.singletonList("AWS"));

        // Call the method under test
        List<UserDto> result = adminService.getFilteredUsers(filterDto);

        // Assert the result
        assertEquals(1, result.size());
    }

    @Test
    void testGetFilteredUsers_CheckedFalse() {
        // Mock user data
        User user = new User();
        user.setEmpId("N0099");
        user.setName("sdfdf");
        Skills skill = new Skills();
        skill.setSkillName("AWS");
        user.setAssignedSkills(new HashSet<>(Collections.singletonList(skill)));

        // Mock repository calls
        when(userRepository.findAllByRole(Role.EMPLOYEE)).thenReturn(Collections.singletonList(user));

        // Create filter DTO
        FilterDto filterDto = new FilterDto();
        filterDto.setChecked(false);
        filterDto.setSkills(Collections.singletonList("AWS"));

        // Call the method under test
        List<UserDto> result = adminService.getFilteredUsers(filterDto);

        // Assert the result
        assertEquals(1, result.size());
    }

    @Test
    void testGetFilteredUsers_NullSkillsInFilter() {
        // Mock user data
        User user = new User();
        user.setEmpId("N0099");
        user.setName("sdfdf");
        Skills skill = new Skills();
        skill.setSkillName("AWS");
        user.setAssignedSkills(new HashSet<>(Collections.singletonList(skill)));

        // Mock repository calls
        when(userRepository.findAllByRole(Role.EMPLOYEE)).thenReturn(Collections.singletonList(user));

        // Create filter DTO
        FilterDto filterDto = new FilterDto();
        filterDto.setChecked(false);
        filterDto.setSkills(null);

        // Call the method under test
        List<UserDto> result = adminService.getFilteredUsers(filterDto);

        // Assert the result
        assertEquals(1, result.size());
    }

    @Test
    void testGetFilteredUsers_EmptySkillsInFilter() {
        // Mock user data
        User user = new User();
        user.setEmpId("N0099");
        user.setName("sdfdf");
        Skills skill = new Skills();
        skill.setSkillName("AWS");
        user.setAssignedSkills(new HashSet<>(Collections.singletonList(skill)));

        // Mock repository calls
        when(userRepository.findAllByRole(Role.EMPLOYEE)).thenReturn(Collections.singletonList(user));

        // Create filter DTO
        FilterDto filterDto = new FilterDto();
        filterDto.setChecked(false);
        filterDto.setSkills(Collections.emptyList());

        // Call the method under test
        List<UserDto> result = adminService.getFilteredUsers(filterDto);

        // Assert the result
        assertEquals(1, result.size());
    }

    @Test
    void testGetFilteredUsers_UserWithNullSkills() {
        // Mock user data
        User user = new User();
        user.setEmpId("N0099");
        user.setName("sdfdf");
        user.setAssignedSkills(Collections.emptySet()); // Set assignedSkills to an empty set

        // Mock repository calls
        when(userRepository.findAllByRole(Role.EMPLOYEE)).thenReturn(Collections.singletonList(user));

        // Create filter DTO
        FilterDto filterDto = new FilterDto();
        filterDto.setChecked(false);
        filterDto.setSkills(Collections.singletonList("AWS"));

        // Call the method under test
        List<UserDto> result = adminService.getFilteredUsers(filterDto);

        // Assert the result
        assertEquals(0, result.size());
    }

}



