package com.employee.Employee.Management.Portal.repository;

import com.employee.Employee.Management.Portal.entity.Resource;
import com.employee.Employee.Management.Portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
    Resource findByEmployeeIdAndManagerId(Long employeeId, User manager);

    List<Resource> findByEmployeeId(Long id);

}




