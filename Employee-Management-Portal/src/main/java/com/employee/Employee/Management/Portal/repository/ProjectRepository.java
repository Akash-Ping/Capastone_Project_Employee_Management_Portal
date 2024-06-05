package com.employee.Employee.Management.Portal.repository;

import com.employee.Employee.Management.Portal.entity.Project;
import com.employee.Employee.Management.Portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

    public interface ProjectRepository extends JpaRepository<Project, Long> {
        List<Project> findAllByManager(User managerId);

//        List<Project> findAllByManagerId(Long id);

    }


