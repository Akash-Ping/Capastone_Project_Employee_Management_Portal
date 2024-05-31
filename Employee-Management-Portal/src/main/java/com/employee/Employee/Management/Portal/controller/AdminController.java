package com.employee.Employee.Management.Portal.controller;

import com.employee.Employee.Management.Portal.dto.ApiResponseDto;
import com.employee.Employee.Management.Portal.dto.AssignProjectDto;
import com.employee.Employee.Management.Portal.dto.AssignProjectOutDto;
import com.employee.Employee.Management.Portal.dto.FilterDto;
import com.employee.Employee.Management.Portal.dto.ManagerOutDto;
import com.employee.Employee.Management.Portal.dto.UserDto;
import com.employee.Employee.Management.Portal.dto.ProjectDto;
import com.employee.Employee.Management.Portal.dto.ProjectOutDto;
import com.employee.Employee.Management.Portal.dto.ManagerInfoDto;
import com.employee.Employee.Management.Portal.dto.RegisterDto;
import com.employee.Employee.Management.Portal.dto.RegisterOutDto;
import com.employee.Employee.Management.Portal.dto.SkillsOutDto;
import com.employee.Employee.Management.Portal.entity.Skills;
import com.employee.Employee.Management.Portal.entity.User;
import com.employee.Employee.Management.Portal.service.AdminService;
import com.employee.Employee.Management.Portal.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * The controller class for managing employee-related operations.
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/employee")
public class AdminController {
    /**
     * Object.
     */
    @Autowired
    private RegisterService registerService;
    @Autowired
    private AdminService adminService;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/all")
    public final List<User> getAllEmployee() {
        logger.info("Started get userDetails controller");
        List<User> users = registerService.getUserDetails();
        logger.info("Ended get userDetails controller");
        return users;
    }

    @GetMapping("/allSkills")
    public final List<Skills> getAllSkills() {
        logger.info("Started get all skills controller");
        return registerService.getAllSkills();
    }

    @PostMapping("/addUser")
    public final ApiResponseDto addUser(@RequestBody final RegisterDto registerDto) {
        logger.info("Started add user controller");
        ApiResponseDto response = registerService.addUser(registerDto);
        logger.info("Ended add user controller");
        return response;
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponseDto> updateUser(@RequestBody final RegisterDto updateUserDto) {
        logger.info("Started update user controller");
        ApiResponseDto response = registerService.updateUser(updateUserDto);
        if ("Employee not found".equals(response.getMessage())) {
            logger.info("Employee not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        logger.info("Ended update user controller");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/getAllEmployees")
    public List<RegisterOutDto> getAllEmployees() {
        logger.info("Started get all employees controller");
        List<RegisterOutDto> employees = adminService.getAllEmployee();
        logger.info("Ended get all employees controller");
        return employees;
    }


    @GetMapping("/Employees/{empId}")
    public SkillsOutDto getEmployeesSkill(@PathVariable final String empId) {
        logger.info("Started get employee skill controller");
        SkillsOutDto employees = adminService.getEmployeeId(empId);
        logger.info("Ended get employee skill controller");
        return employees;
    }
    @GetMapping("/getAllEmployeesAndManagers")
    public List<RegisterOutDto> getAllEmployeesAndManagers() {
        logger.info("Started get all employees and managers controller");
        List<RegisterOutDto> employeesAndManagers = adminService
                .getAllEmployeeAndManager();
        logger.info("Ended get all employees and managers controller");
        return employeesAndManagers;
    }

    @GetMapping("/getAllManagers")
    public List<ManagerOutDto> getAllManagers() {
        logger.info("Started get all managers controller");
        List<ManagerOutDto> allManagers = adminService.getAllManagers();
        logger.info("Ended get all managers controller");
        return allManagers;
    }

    @GetMapping("/getAllManagersInfo")
    public List<ManagerInfoDto> getAllManagersInfo() {
        logger.info("Started get all managers info controller");
        List<ManagerInfoDto> managerInfoList = adminService.getAllManagersInfo();
        logger.info("Ended get all managers info controller");
        return managerInfoList;
    }

    @PostMapping("/addProject")
    public ApiResponseDto addProject(@RequestBody final ProjectDto projectDto) {
        logger.info("Started add project controller");
        ApiResponseDto response = adminService.addProject(projectDto);
        logger.info("Ended add project controller");
        return response;

    }

    @GetMapping("/getAllProjects")
    public List<ProjectDto> getAllProjects() {
        logger.info("Started get all projects controller");
        List<ProjectDto> projectDtoList = adminService.getAllProjects();
        logger.info("Ended get all projects controller");
        return projectDtoList;
    }

    @GetMapping("/getAllProjectsForAssign")
    public List<AssignProjectOutDto> getAllProjectsForAssign() {
        logger.info("Started get all projects for assign controller");
        List<AssignProjectOutDto> assignProjectOutDtoList = adminService
                .getAllProjectsForAssign();
        logger.info("Ended get all projects for assign controller");
        return assignProjectOutDtoList;
    }


    @GetMapping("/getAll/project/{managerId}")
    public List<ProjectOutDto> getAllByManagerId(@PathVariable final Long managerId) {
        logger.info("Started get all projects by manager id controller");
        List<ProjectOutDto> projectOutDtoList = adminService.getAllByManagerId(managerId);
        logger.info("Ended get all projects by manager id controller");
        return projectOutDtoList;
    }


    @PostMapping("/assignProject")
    public ApiResponseDto assignProject(
             @RequestBody final AssignProjectDto assignProjectDto) {
        logger.info("Started assign project controller");
        ApiResponseDto apiResponseDto = adminService
                .assignProject(assignProjectDto);
        logger.info("Ended assign project controller");
        return apiResponseDto;
    }



    @PostMapping("/filter")
    public List<UserDto> getFilteredUsers(@RequestBody final FilterDto filterDto) {
        logger.info("Started get filtered users controller");
        List<UserDto> filteredUsers = adminService.getFilteredUsers(filterDto);
        logger.info("Ended get filtered users controller");
        return filteredUsers;
    }

    @PutMapping("/unassignProject/{empId}")
    public ApiResponseDto unassignProject(@PathVariable final String empId) {
        logger.info("Started unassign project controller");
        adminService.unassignProject(empId);
       ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setMessage("Project unassigned successfully");
        logger.info("Ended unassign project controller");
        return apiResponseDto;

    }

    @DeleteMapping("/delete/{empId}")
    public ApiResponseDto deleteEmployee(@PathVariable final String empId) {
        logger.info("Started delete employee controller");
        adminService.deleteUser(empId);
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setMessage("Employee deleted successfully");
        logger.info("Ended delete employee controller");
        return apiResponseDto;

    }

}
