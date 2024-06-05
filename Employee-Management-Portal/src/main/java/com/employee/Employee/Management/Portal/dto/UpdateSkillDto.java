package com.employee.Employee.Management.Portal.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UpdateSkillDto {

    private Set<Long> skillIdsToAdd;
    private Set<Long> skillIdsToRemove;
}
