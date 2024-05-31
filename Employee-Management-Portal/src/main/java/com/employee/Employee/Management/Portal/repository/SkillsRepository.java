package com.employee.Employee.Management.Portal.repository;

import com.employee.Employee.Management.Portal.entity.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Long> {

//    Optional<Skills> findBySkill_Name(String skillName);
}
