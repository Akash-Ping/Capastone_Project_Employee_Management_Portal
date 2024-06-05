package com.employee.Employee.Management.Portal.repository;

import com.employee.Employee.Management.Portal.entity.Role;
import com.employee.Employee.Management.Portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByRole(Role role);

    Optional<User> findByEmail(String email);

    List<User> findAllByRole(Role role);

    List<User> findAllByRoleNot(Role role);


    List<User> findAllByEmpProjectId(Long empProjectId);

    Optional<User> findByEmpId(String empId);

    void deleteByEmpId(String empId);

}
