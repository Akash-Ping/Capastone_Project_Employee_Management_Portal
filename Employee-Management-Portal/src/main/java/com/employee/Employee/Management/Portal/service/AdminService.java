package com.employee.Employee.Management.Portal.service;

import com.employee.Employee.Management.Portal.dto.RegisterOutDto;
import com.employee.Employee.Management.Portal.dto.ManagerOutDto;
import com.employee.Employee.Management.Portal.dto.ManagerInfoDto;
import com.employee.Employee.Management.Portal.dto.ApiResponseDto;
import com.employee.Employee.Management.Portal.dto.AssignProjectDto;
import com.employee.Employee.Management.Portal.dto.AssignProjectOutDto;
import com.employee.Employee.Management.Portal.dto.FilterDto;
import com.employee.Employee.Management.Portal.dto.ProjectDto;
import com.employee.Employee.Management.Portal.dto.ProjectOutDto;
import com.employee.Employee.Management.Portal.dto.SkillsOutDto;
import com.employee.Employee.Management.Portal.dto.UserDto;
import com.employee.Employee.Management.Portal.entity.Project;
import com.employee.Employee.Management.Portal.entity.Role;
import com.employee.Employee.Management.Portal.entity.Skills;
import com.employee.Employee.Management.Portal.entity.User;
import com.employee.Employee.Management.Portal.repository.ProjectRepository;
import com.employee.Employee.Management.Portal.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;
    public List<RegisterOutDto> getAllEmployee() {
        List<User> managerEntities = userRepository.findAllByRole(Role.EMPLOYEE);
        return convertToRegisterDto(managerEntities);
    }

    public List<RegisterOutDto> getAllEmployeeAndManager() {
        List<User> employeeAndManagerEntities = userRepository.findAllByRoleNot(Role.ADMIN);
        return convertToRegisterDto(employeeAndManagerEntities);
    }

    private List<RegisterOutDto> convertToRegisterDto(final List<User> managerEntities) {
        List<RegisterOutDto> registerDtos = new ArrayList<>();
        for (User user : managerEntities) {
            RegisterOutDto registerOutDto = new RegisterOutDto();
            registerOutDto.setId(user.getId());
            registerOutDto.setEmpId(user.getEmpId());
            registerOutDto.setName(user.getName());
            registerOutDto.setEmail(user.getEmail());
            registerOutDto.setContactNo(user.getContactNo());
            registerOutDto.setDesignation(user.getDesignation());
            registerOutDto.setDob(user.getDob());
            registerOutDto.setDoj(user.getDoj());
            registerOutDto.setLocation(user.getLocation());
            registerOutDto.setEmpProjectId(user.getEmpProjectId());
            registerOutDto.setRole(user.getRole());
            registerOutDto.setAssignedSkills(user.getAssignedSkills());


            if (user.getEmpManagerId() != null) {
                Optional<User> manager = userRepository.findById(user.getEmpManagerId());
                if (manager.isPresent()) {
                   User managerUser = manager.get();
                     registerOutDto.setManagerName(managerUser.getName());
                     }
                 }

            if (user.getEmpProjectId() != null) {
                Optional<Project> projectEntity = projectRepository.findById(user.getEmpProjectId());

                if (projectEntity != null) {
                    registerOutDto.setProjectName(projectEntity.get().getProjectName());
                }
            }
            registerDtos.add(registerOutDto);
        }
        return registerDtos;

    }

    public List<ManagerOutDto> getAllManagers() {
        List<User> managerEntities = userRepository
                .findAllByRole(Role.MANAGER);
        return convertToManagerDtoList(managerEntities);
    }

    private List<ManagerOutDto> convertToManagerDtoList(final List<User> managerEntities) {
        List<ManagerOutDto> managerOutDtos = new ArrayList<>();
        for (User user : managerEntities) {
            ManagerOutDto managerOutDto = new ManagerOutDto();
            managerOutDto.setId(user.getId());
            managerOutDto.setEmpId(user.getEmpId());
            managerOutDto.setName(user.getName());
            managerOutDto.setEmail(user.getEmail());
            managerOutDto.setContactNo(user.getContactNo());
            managerOutDto.setDesignation(user.getDesignation());
            managerOutDto.setLocation(user.getLocation());
            managerOutDtos.add(managerOutDto);
        }
        return managerOutDtos;
    }

    public List<ManagerInfoDto> getAllManagersInfo() {
        List<User> managerEntities = userRepository
                .findAllByRole(Role.MANAGER);
        return managerEntities.stream().map(this::convertToManagerInfoDto)
                .collect(Collectors.toList());
    }

    private ManagerInfoDto convertToManagerInfoDto(final User user) {
        ManagerInfoDto managerInfoDto = new ManagerInfoDto();
        managerInfoDto.setId(user.getId());
        managerInfoDto.setManagerName(user.getName());
        managerInfoDto.setManagerEmployeeId(user.getEmpId());
        return managerInfoDto;
    }

    public ApiResponseDto addProject(final ProjectDto projectDto) {

        // Check if the start date is in the past
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = LocalDate.parse(projectDto.getStartDate());
        if (startDate.isBefore(currentDate)) {
            return new ApiResponseDto("Start date cannot be in the past");
        }

        Project projectEntity = new Project();
        projectEntity.setProjectName(projectDto.getProjectName());
        projectEntity.setDescription(projectDto.getDescription());
        projectEntity.setStartDate(projectDto.getStartDate());
        Optional<User> user = userRepository.findById(projectDto.getManager());
        projectEntity.setManager(user.get());
        projectRepository.save(projectEntity);
        return new ApiResponseDto("Project added successfully");
    }

    public List<ProjectDto> getAllProjects() {
        List<Project> projectEntities = projectRepository.findAll();
        List<ProjectDto> projectDtos = convertToProjectDtoList(projectEntities);
        return projectDtos;
    }

    private List<ProjectDto> convertToProjectDtoList(final List<Project> entities) {
        List<ProjectDto> dtos = new ArrayList<>();
        for (Project project : entities) {
            ProjectDto projectDto = new ProjectDto();
            projectDto.setId(project.getId());
            projectDto.setProjectName(project.getProjectName());
            projectDto.setDescription(project.getDescription());
            projectDto.setStartDate(project.getStartDate());
            projectDto.setManager(project.getManager().getId());

            List<User> teamMembers = userRepository.findAllByEmpProjectId(project.getId());
            Optional<User> headEntity = userRepository.findById(project.getManager().getId());
            if (headEntity.isPresent()) {
                projectDto.setHead(headEntity.get().getName());
            }
            List<String> teamMemberNames = new ArrayList<>();
            for (User teamMember : teamMembers) {
                teamMemberNames.add(teamMember.getName());
            }
            projectDto.setTeamMembers(teamMemberNames);
            dtos.add(projectDto);
        }
        return dtos;
    }

    public List<AssignProjectOutDto> getAllProjectsForAssign() {
        List<Project> projectEntities = projectRepository.findAll();
       List<AssignProjectOutDto> assignProjectOutDtos = projectEntities.stream().map(project -> {
            AssignProjectOutDto assignProjectOutDto = new AssignProjectOutDto();
            assignProjectOutDto.setId(project.getId());
            assignProjectOutDto.setProjectName(project.getProjectName());
            return assignProjectOutDto;
        }).collect(Collectors.toList());
       return assignProjectOutDtos;

    }


    public List<ProjectOutDto> getAllByManagerId(final Long managerId) {
        // Fetch the manager entity by ID
        User manager = userRepository.findById(managerId).orElse(null);

        List<ProjectOutDto> projectOutDtos = new ArrayList<ProjectOutDto>();

        if (manager != null) {
            // Query projects by manager ID
            List<Project> projectEntities = projectRepository.findAllByManager(manager);

            for (Project project : projectEntities) {
                ProjectOutDto projectOutDto = new ProjectOutDto();
                projectOutDto.setId(project.getId());
                projectOutDto.setProjectName(project.getProjectName());
                // Convert manager ID to string
                projectOutDto.setManager(String.valueOf(managerId));
//                projectOutDto.setManager(project.getManager() + "");
                projectOutDto.setDescription(project.getDescription());
                projectOutDto.setStartDate(project.getStartDate());

                // Fetch team members for the project
                List<User> teamMembers = userRepository.findAllByEmpProjectId(project.getId());

                List<String> teamMemberNames = new ArrayList<>();
                for (User teamMember : teamMembers) {
                    teamMemberNames.add(teamMember.getName());
                }
                projectOutDto.setTeamMembers(teamMemberNames);
                projectOutDtos.add(projectOutDto);
            }
        }

        return projectOutDtos;
    }

    public ApiResponseDto assignProject(final AssignProjectDto assignProjectDto) {
        User user = userRepository.findByEmpId(assignProjectDto.getEmpId()).orElse(null);
        Optional<Project> project = projectRepository.findById(assignProjectDto.getId());
        user.setEmpManagerId(project.get().getManager().getId());
        user.setEmpProjectId(assignProjectDto.getId());
        userRepository.save(user);

        return new ApiResponseDto("Project assigned successfully");
    }


    public void unassignProject(final String empId) {
        User employee = userRepository.findByEmpId(empId).orElse(null);
        User admin = userRepository.findByRole(Role.ADMIN).orElse(null);
        employee.setEmpProjectId(null);
        employee.setEmpManagerId(admin.getId());
        userRepository.save(employee);
    }

    @Transactional
    public void deleteUser(final String empId) {
        userRepository.deleteByEmpId(empId);
    }

    public SkillsOutDto getEmployeeId(final String empId) {
        User employee = userRepository.findByEmpId(empId).orElse(null);
        SkillsOutDto skillsOutDto = new SkillsOutDto();
        if (employee != null) {
            skillsOutDto.setAssignedSkills(employee.getAssignedSkills());
        }
        return skillsOutDto;
    }


    // Servic for Update Employee
    public List<UserDto> getFilteredUsers(final FilterDto filterDto) {
        List<User> userList = userRepository.findAllByRole(Role.EMPLOYEE);
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : userList) {
            UserDto userDto = new UserDto();
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setEmpId(user.getEmpId());
            userDto.setDesignation(user.getDesignation());
            userDto.setContactNo(user.getContactNo());
            userDto.setDob(user.getDob());
            userDto.setDoj(user.getDoj());
            userDto.setLocation(user.getLocation());
            Set<String> skillNames = user.getAssignedSkills().stream()
                    .map(Skills::getSkillName) // Extracting skill names
                    .collect(Collectors.toSet()); // Collecting them into a set
            userDto.setAssignedSkills(skillNames);

            if (user.getEmpProjectId() == null) {
                userDto.setProjectName("N/A");
                userDto.setProjectId(null); // Adjusted to set null for projectId
            } else {
                Project project = projectRepository.findById(user.getEmpProjectId()).orElse(null);
                if (project != null) {
                    userDto.setProjectName(project.getProjectName());
                    userDto.setProjectId(user.getEmpProjectId());
                } else {
                    userDto.setProjectName("N/A");
                    userDto.setProjectId(null); // Adjusted to set null for projectId
                }
            }

            User manager = userRepository.findById(user.getEmpManagerId()).orElse(null);
            if (manager != null) {
                userDto.setManagerName(manager.getName());
            } else {
                userDto.setManagerName("N/A");
            }

            if (filterDto.getSkills() == null || filterDto.getSkills().isEmpty()) {
                if (filterDto.getChecked() && userDto.getProjectName().equals("N/A")) {
                    userDtoList.add(userDto);
                } else if (!filterDto.getChecked()) {
                    userDtoList.add(userDto);
                }
            } else {
                boolean hasMatchingSkills = user.getAssignedSkills().stream()
                        .map(Skills::getSkillName) // Extracting skill names
                        .anyMatch(skillName -> filterDto.getSkills().contains(skillName));
                if (filterDto.getChecked() && hasMatchingSkills && userDto.getProjectName().equals("N/A")) {
                    userDtoList.add(userDto);
                } else if (!filterDto.getChecked() && hasMatchingSkills) {
                    userDtoList.add(userDto);
                }
            }
        }

        return userDtoList;
    }

}
