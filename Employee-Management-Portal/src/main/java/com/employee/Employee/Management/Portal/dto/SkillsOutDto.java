package com.employee.Employee.Management.Portal.dto;

import com.employee.Employee.Management.Portal.entity.Skills;
import lombok.Data;

import java.util.Set;

@Data
public class SkillsOutDto {

    private Set<Skills> assignedSkills;
}
