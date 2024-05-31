package com.employee.Employee.Management.Portal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.employee.Employee.Management.Portal.dto.ApiResponseDto;
import com.employee.Employee.Management.Portal.dto.RequestResourceDto;
import com.employee.Employee.Management.Portal.dto.RequestedDto;
import com.employee.Employee.Management.Portal.dto.RequestedOutDto;
import com.employee.Employee.Management.Portal.dto.RequestResourceManagerProjectDto;
import com.employee.Employee.Management.Portal.dto.ResourceRequestsAdminOutDto;
import com.employee.Employee.Management.Portal.service.RequestResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ResourceController {


    @Autowired
    private RequestResourceService requestResourceService;

    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @PostMapping("/requestResource/create")
    public ApiResponseDto createRequestResource(
            @RequestBody final RequestResourceDto requestResourceDto) {
        logger.info("Started create request resource controller");
        requestResourceService.createRequestResource(requestResourceDto);
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setMessage("Requested resource successfully");
        logger.info("Ended create request resource controller");
        return apiResponseDto;

    }

    @PostMapping("/requestResource/isRequested")
    public RequestedOutDto isRequested(
             @RequestBody final RequestedDto requestedDto) {
        logger.info("Started is requested controller");
        RequestedOutDto requestedOutDto = requestResourceService
                .isRequested(requestedDto);
        logger.info("Ended is requested controller");
        return requestedOutDto;
    }

    @GetMapping("/getAll/project/byManager/{email}")
    public final List<RequestResourceManagerProjectDto> getAllByManagerEmail(
            @PathVariable final String email) {
        logger.info("Started get all by manager email controller");
        List<RequestResourceManagerProjectDto> projectList = requestResourceService
                .getAllByManagerEmail(email);
        logger.info("Ended get all by manager email controller");
        return projectList;
    }

    @GetMapping("/requestResource/getAll/requests")
    public List<ResourceRequestsAdminOutDto> getAllResourceRequests() {
        logger.info("Started get all resource requests controller");
        List<ResourceRequestsAdminOutDto> outDtoList = requestResourceService
                .getAllResourceRequests();
        logger.info("Ended get all resource requests controller");
        return outDtoList;

    }

    @DeleteMapping("/requestResource/reject/{id}")
    public ApiResponseDto rejectResourceRequest(@PathVariable final Long id) {
        logger.info("Started reject resource request controller");
        requestResourceService.rejectResourceRequest(id);
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setMessage("Resource request rejected successfully");
        logger.info("Ended reject resource request controller");
        return apiResponseDto;
    }

    @PostMapping("/request/accept/{id}")
    public final ApiResponseDto acceptRequest(@PathVariable final Long id) {
        logger.info("Started accept request controller");
        ApiResponseDto apiResponseDto = requestResourceService.acceptRequest(id);
        logger.info("Ended accept request controller");
        return apiResponseDto;
    }
}
