package com.employee.Employee.Management.Portal.service;

import com.employee.Employee.Management.Portal.dto.ApiResponseDto;
import com.employee.Employee.Management.Portal.dto.RegisterDto;
import com.employee.Employee.Management.Portal.entity.Role;
import com.employee.Employee.Management.Portal.entity.Skills;
import com.employee.Employee.Management.Portal.entity.User;
import com.employee.Employee.Management.Portal.exception.DataAlreadyExistsException;
import com.employee.Employee.Management.Portal.exception.InvalidEmailDomainException;
import com.employee.Employee.Management.Portal.repository.SkillsRepository;
import com.employee.Employee.Management.Portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SkillsRepository skillsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public final ApiResponseDto adminRegister(final RegisterDto registerDto) {
        // Logging to check the email
        System.out.println("Registering with email: " + registerDto.getEmail());

        if (!registerDto.getEmail().equalsIgnoreCase("admin@nucleusteq.com")) {
            ApiResponseDto response = new ApiResponseDto();
            response.setMessage("You are not authorized to register as admin");
            return response;
        }

        // Logging to check the current admins in the database
        System.out.println("Checking if an admin already exists");
        if (userRepository.findByRole(Role.ADMIN).isPresent()) {
            ApiResponseDto response = new ApiResponseDto();
            System.out.println("Admin already registered");
            response.setMessage("Admin already registered");
            return response;
        }

        User adminUser = new User();

        adminUser.setRole(Role.ADMIN);
        adminUser.setEmpManagerId(0L);
        adminUser.setEmail(registerDto.getEmail());
        adminUser.setName(registerDto.getName());
        adminUser.setEmpId(registerDto.getEmpId());
        adminUser.setDesignation(registerDto.getDesignation());
        adminUser.setContactNo(registerDto.getContactNo());
        adminUser.setDob(registerDto.getDob());
        adminUser.setDoj(registerDto.getDoj());
        adminUser.setLocation(registerDto.getLocation());
        String hashedPassword = passwordEncoder.encode(registerDto.getPassword());
        adminUser.setPassword(hashedPassword);
        adminUser.setEmpManagerId(registerDto.getEmpManagerId());

        // Save the admin user
        this.userRepository.save(adminUser);
        System.out.println("Admin registered successfully");

        ApiResponseDto response = new ApiResponseDto();
        response.setMessage("Admin registered successfully");
        return response;
    }


    public ApiResponseDto addUser(final RegisterDto addUserDto) throws DataAlreadyExistsException {
        ApiResponseDto response = new ApiResponseDto();



        // Check if the email has the correct domain
        if (!addUserDto.getEmail().endsWith("@nucleusteq.com")) {
            throw new InvalidEmailDomainException("Email must be a nucleusteq.com address");
        }

        Optional<User> emailStyle = userRepository.findByEmail(addUserDto.getEmail());
        if (emailStyle.isPresent()) {
            response.setMessage("Email address already in use");
            return response;
        }

        // Check if the email already exists
        Optional<User> existingUser = userRepository.findByEmail(addUserDto.getEmail());
        if (existingUser.isPresent()) {
            response.setMessage("Email address already in use");
            return response;
        }

        // Check if the empId already exists
        Optional<User> existingUserByEmpId = userRepository.findByEmpId(addUserDto.getEmpId());
        if (existingUserByEmpId.isPresent()) {
            response.setMessage("Employee ID already in use");
            return response;
        }

        // Set the manager ID if an admin role is present
        Optional<User> userRole = userRepository.findByRole(Role.ADMIN);
        if (userRole.isPresent()) {
            addUserDto.setEmpManagerId(userRole.get().getId());
        } else {
            addUserDto.setEmpManagerId(null);
        }


        User user = new User();
        user.setName(addUserDto.getName());
        user.setRole(addUserDto.getRole());
        user.setEmpProjectId(addUserDto.getEmpProjectId());
        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(addUserDto.getPassword());
        user.setPassword(hashedPassword);
        user.setDob(addUserDto.getDob());
        user.setDoj(addUserDto.getDoj());
        user.setRole(addUserDto.getRole());
        user.setEmail(addUserDto.getEmail());
        user.setEmpId(addUserDto.getEmpId());
        user.setDesignation(addUserDto.getDesignation());
        user.setContactNo(addUserDto.getContactNo());
        user.setLocation(addUserDto.getLocation());
        user.setEmpManagerId(addUserDto.getEmpManagerId());

        // Fetch skills from database based on skill IDs
        List<Long> skillIds = new ArrayList<>(addUserDto.getAssignedSkills());
        List<Skills> skills = skillsRepository.findAllById(skillIds);

        // Associate skills with the user
        Set<Skills> assignedSkills = new HashSet<>(skills);
        user.setAssignedSkills(assignedSkills);

        this.userRepository.save(user);
        response.setMessage("Employee added successfully");
        return response;
    }

    public ApiResponseDto updateUser(final RegisterDto updateUserDto) {
        ApiResponseDto response = new ApiResponseDto();

        Optional<User> optionalUser = userRepository.findByEmpId(updateUserDto.getEmpId());
        if (!optionalUser.isPresent()) {
            response.setMessage("Employee not found");
            return response;
        }

        User user = optionalUser.get();

        if (updateUserDto.getName() != null) user.setName(updateUserDto.getName());
        if (updateUserDto.getDob() != null) user.setDob(updateUserDto.getDob());
        if (updateUserDto.getDoj() != null) user.setDoj(updateUserDto.getDoj());
        if (updateUserDto.getEmail() != null) user.setEmail(updateUserDto.getEmail());
        if (updateUserDto.getDesignation() != null) user.setDesignation(updateUserDto.getDesignation());
        if (updateUserDto.getContactNo() != null) user.setContactNo(updateUserDto.getContactNo());
        if (updateUserDto.getLocation() != null) user.setLocation(updateUserDto.getLocation());


        this.userRepository.save(user);
        response.setMessage("Employee updated successfully");
        return response;
    }


    public List<User> getUserDetails() {
        return userRepository.findAll();
    }

    public List<Skills> getAllSkills() {
        return skillsRepository.findAll();
    }

}
