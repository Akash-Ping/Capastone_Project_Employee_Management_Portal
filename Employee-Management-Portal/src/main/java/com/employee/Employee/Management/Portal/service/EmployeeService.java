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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {


    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SkillsRepository skillsRepository;


    public EmpDto getEmployee(final String email) throws WrongInputException {
        User user = userRepository.findByEmail(email).orElse(null);

        EmpDto resultDto = new EmpDto();
        resultDto.setId(user.getId());
        resultDto.setEmpId(user.getEmpId());
        resultDto.setName(user.getName());
        resultDto.setEmail(user.getEmail());
        resultDto.setContactNo(user.getContactNo());
        resultDto.setDob(user.getDob());
        resultDto.setDoj(user.getDoj());
        resultDto.setLocation(user.getLocation());
        resultDto.setDesignation(user.getDesignation());
        Set<String> assignedSkillIds = user.getAssignedSkills().stream()
                .map(Skills::getSkillName)
                .collect(Collectors.toSet());
        resultDto.setAssignedSkills(assignedSkillIds);
        if (user.getEmpProjectId() == null) {
            resultDto.setProjectName("N/A");
        } else {
            Optional<Project> project = projectRepository.findById(user.getEmpProjectId());
            if (!project.isPresent()) {
                throw new WrongInputException("Project not found");
            }
            resultDto.setProjectName(project.get().getProjectName());
        }
        User manager = userRepository.findById(user.getEmpManagerId()).orElse(null);
        if (manager == null) {
            throw new WrongInputException("Manager not found");
        }
        resultDto.setManagerName(manager.getName());
        return resultDto;
    }


    // Check for the skills of the employee and update the skills
    // very verY important
    public ApiResponseDto updateSkill(final String userId,
                                      final Set<Long> skillIdsToAdd,
                                      final Set<Long> skillIdsToRemove) {
        User user = userRepository.findByEmpId(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        // Add new skills
        for (Long skillId : skillIdsToAdd) {
            Skills skill = skillsRepository.findById(skillId)
                    .orElseThrow(() -> new EntityNotFoundException("Skill not found with id: " + skillId));
            user.getAssignedSkills().add(skill); // Corrected to getAssignedSkills()
        }

        // Remove existing skills
        for (Long skillId : skillIdsToRemove) {
            Skills skill = skillsRepository.findById(skillId)
                    .orElseThrow(() -> new EntityNotFoundException("Skill not found with id: " + skillId));
            user.getAssignedSkills().remove(skill); // Corrected to getAssignedSkills()
        }

        // Save the updated user entity back to the database
        userRepository.save(user);

        // Return appropriate response
        return new ApiResponseDto("User skills updated successfully"); // Provide appropriate response


}

    public ApiResponseDto updatePassword(final String email, final RegisterDto registerDto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        String hashedPassword = passwordEncoder.encode(registerDto.getPassword());
        // Update the password
        user.setPassword(hashedPassword);

        // Save the updated user entity back to the database
        userRepository.save(user);

        // Return appropriate response
        return new ApiResponseDto("Password updated successfully"); // Provide appropriate response
    }
}
