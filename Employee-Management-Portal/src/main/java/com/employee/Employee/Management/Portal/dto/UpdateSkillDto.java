package com.employee.Employee.Management.Portal.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UpdateSkillDto {

    private Set<Long> skillIdsToAdd;
    private Set<Long> skillIdsToRemove;

    public Set<Long> getSkillIdsToRemove() {
        return skillIdsToRemove;
    }

    public void setSkillIdsToRemove(Set<Long> skillIdsToRemove) {
        this.skillIdsToRemove = skillIdsToRemove;
    }

    public Set<Long> getSkillIdsToAdd() {
        return skillIdsToAdd;
    }

    public void setSkillIdsToAdd(Set<Long> skillIdsToAdd) {
        this.skillIdsToAdd = skillIdsToAdd;


    }

    @Override
    public String toString() {
        return "UpdateSkillDto{"
                + "skillIdsToAdd=" + skillIdsToAdd
                + ", skillIdsToRemove=" + skillIdsToRemove
                + '}';
    }
}
