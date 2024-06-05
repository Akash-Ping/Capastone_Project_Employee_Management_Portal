package com.employee.Employee.Management.Portal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.employee.Employee.Management.Portal.dto.ApiResponseDto;
import com.employee.Employee.Management.Portal.dto.RegisterDto;
import com.employee.Employee.Management.Portal.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @PostMapping("/register")
    public final ApiResponseDto registerAdmin(@RequestBody final RegisterDto registerDto) {
        logger.info("Started register controller");
        ApiResponseDto response = registerService.adminRegister(registerDto);
        logger.info("Ended register controller");
        return response;
    }


}
