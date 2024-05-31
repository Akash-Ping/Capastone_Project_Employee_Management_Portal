package com.employee.Employee.Management.Portal.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.employee.Employee.Management.Portal.dto.ApiResponseDto;
import com.employee.Employee.Management.Portal.dto.EmpDto;
import com.employee.Employee.Management.Portal.dto.RegisterDto;
import com.employee.Employee.Management.Portal.dto.UpdateSkillDto;
import com.employee.Employee.Management.Portal.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping("/{email}")
    public EmpDto getEmployeeByEmail(@PathVariable final String email) {
        logger.info("Started get employee by email controller");
        EmpDto registerDto = employeeService.getEmployee(email);
        logger.info("Ended get employee by email controller");
        return registerDto;
    }


    @PutMapping("/{userId}/updateskills")
    public ApiResponseDto updateSkillsOfEmployee(@PathVariable final String userId,
                                                 @RequestBody final UpdateSkillDto updateSkillDto) {
        logger.info("Started update skills of employee controller");
        ApiResponseDto apiResponseDto = employeeService
                .updateSkill(userId, updateSkillDto.getSkillIdsToAdd(), updateSkillDto.getSkillIdsToRemove());
        logger.info("Ended update skills of employee controller");
        return apiResponseDto;
    }

    @PutMapping("/{email}/updatepassword")
    public ApiResponseDto updatePassword(@PathVariable final String email, @RequestBody final RegisterDto registerDto) {
        logger.info("Started update password controller");
        ApiResponseDto apiResponseDto = employeeService.updatePassword(email, registerDto);
        logger.info("Ended update password controller");
        return apiResponseDto;
    }



}
