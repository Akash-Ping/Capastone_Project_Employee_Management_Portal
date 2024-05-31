package com.employee.Employee.Management.Portal.dto;

import com.employee.Employee.Management.Portal.entity.Skills;
import lombok.Data;

import java.util.Set;

@Data
public class SkillsOutDto {

    private Set<Skills> assignedSkills;

    public Set<Skills> getAssignedSkills() {
        return assignedSkills;
    }

    public void setAssignedSkills(Set<Skills> assignedSkills) {
        this.assignedSkills = assignedSkills;
    }

    @Override
    public String toString() {
        return "SkillsOutDto{"
                + "assignedSkills=" + assignedSkills
                + '}';
    }
}
