package com.employee.Employee.Management.Portal.service;

import com.employee.Employee.Management.Portal.dto.RequestedOutDto;
import com.employee.Employee.Management.Portal.dto.RequestedDto;
import com.employee.Employee.Management.Portal.dto.RequestResourceDto;
import com.employee.Employee.Management.Portal.dto.RequestResourceManagerProjectDto;
import com.employee.Employee.Management.Portal.dto.ResourceRequestsAdminOutDto;
import com.employee.Employee.Management.Portal.dto.ApiResponseDto;
import com.employee.Employee.Management.Portal.entity.Project;
import com.employee.Employee.Management.Portal.entity.Resource;
import com.employee.Employee.Management.Portal.entity.User;
import com.employee.Employee.Management.Portal.repository.ProjectRepository;
import com.employee.Employee.Management.Portal.repository.ResourceRepository;
import com.employee.Employee.Management.Portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RequestResourceService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private ProjectRepository projectRepository;


    public void createRequestResource(final RequestResourceDto requestResourceDto) {
        Optional<User> employee = userRepository.findByEmpId(requestResourceDto.getEmpId());
        Optional<User> managerOptional = userRepository.findByEmail(requestResourceDto.getEmail());

        if (employee.isPresent() && managerOptional.isPresent()) {
            User manager = managerOptional.get();

            Resource resource = new Resource();
            resource.setEmployeeId(employee.get().getId());
            resource.setManagerId(manager); // Set manager directly
            resource.setProjectId(projectRepository.findById(requestResourceDto.getProjectId()).orElse(null));
            // Assuming you have a project repository
            resource.setComment(requestResourceDto.getComment());

            resourceRepository.save(resource);
        } else {
            throw new RuntimeException("Employee or Manager not found");
        }
    }

    public RequestedOutDto isRequested(final RequestedDto requestedDto) {
        RequestedOutDto requestedOutDto = new RequestedOutDto();
        Optional<User> employee = userRepository.findByEmpId(requestedDto.getEmpId());
        Optional<User> manager = userRepository.findByEmail(requestedDto.getEmail());
        User managerUser = manager.get();
        if (resourceRepository.findByEmployeeIdAndManagerId(employee.get().getId(), managerUser) != null) {
            requestedOutDto.setRequested(true);
        } else {
            requestedOutDto.setRequested(false);
        }
        return requestedOutDto;
    }

    public List<RequestResourceManagerProjectDto> getAllByManagerEmail(final String email) {
        Optional<User> manager = userRepository.findByEmail(email);
//        User managers = userRepository.findById(managerId).orElse(null);
        List<Project> projectList = projectRepository.findAllByManager(manager.get());
        List<RequestResourceManagerProjectDto> projectOutList = new ArrayList<RequestResourceManagerProjectDto>();

         for (Project project : projectList) {
            RequestResourceManagerProjectDto projectOut = new RequestResourceManagerProjectDto();
            projectOut.setId(project.getId());
            projectOut.setProjectName(project.getProjectName());
            projectOutList.add(projectOut);
        }
        return projectOutList;

    }

    public List<ResourceRequestsAdminOutDto> getAllResourceRequests() {
        List<Resource> requestList = resourceRepository.findAll();

         if (requestList.isEmpty()) {
            throw new RuntimeException("No requests found");
        }

        List<ResourceRequestsAdminOutDto> outRequestList = new ArrayList<ResourceRequestsAdminOutDto>();
         for (Resource resource : requestList) {
            ResourceRequestsAdminOutDto outRequest = new ResourceRequestsAdminOutDto();

            User employee = userRepository.findById(resource.getEmployeeId()).orElse(null);
                if (employee == null) {
                resourceRepository.deleteById(resource.getId());
            }

            User manager = userRepository.findById(resource.getManagerId().getId()).orElse(null);

            Project project = projectRepository.findById(resource.getProjectId().getId()).orElse(null);

            outRequest.setEmployeeName(employee.getName());
            outRequest.setManagerName(manager.getName());
            outRequest.setProjectName(project.getProjectName());
            outRequest.setComment(resource.getComment());
            outRequest.setId(resource.getId());
            outRequest.setEmployeeId(resource.getEmployeeId());
            outRequest.setManagerId(resource.getManagerId().getId());
            outRequest.setProjectId(resource.getProjectId().getId());
            outRequestList.add(outRequest);
        }
        return outRequestList;

    }

    public void rejectResourceRequest(final Long id) {
        resourceRepository.deleteById(id);
    }

    public ApiResponseDto acceptRequest(final Long id) {
        Resource resource = resourceRepository.findById(id).orElse(null);
            if (resource == null) {
            throw new RuntimeException("Resource not found");
        }
        User employee = userRepository.findById(resource.getEmployeeId()).orElse(null);
        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }

        if (employee.getEmpProjectId() != null) {
            throw new RuntimeException("Employee already assigned to a project");
        }

        employee.setEmpProjectId(resource.getProjectId().getId());
        employee.setEmpManagerId(resource.getManagerId().getId());
        userRepository.save(employee);
        rejectResourceRequest(id);

        List<Resource> employeeRequests = resourceRepository.findByEmployeeId(employee.getId());
            for (Resource request : employeeRequests) {
            rejectResourceRequest(request.getId());
            }
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setMessage("Resource request accepted successfully");
        return apiResponseDto;
        }

}
