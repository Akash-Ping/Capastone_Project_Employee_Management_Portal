package com.employee.Employee.Management.Portal.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class FilterDto {

    private List<String> skills;

    private boolean checked;

    public FilterDto() {
        this.skills = new ArrayList<>();
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(final boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "FilterDto [skills=" + skills + ", checked=" + checked + "]";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterDto filterDto = (FilterDto) o;
        return checked == filterDto.checked && Objects.equals(skills, filterDto.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skills, checked);
    }

    public List<String> getSkills() {
        if (skills == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(skills);
    }

    public void setSkills(final List<String> skills) {
        if (skills == null) {
            this.skills = new ArrayList<>();
        } else {
            this.skills = new ArrayList<>(skills);
        }
    }
}
