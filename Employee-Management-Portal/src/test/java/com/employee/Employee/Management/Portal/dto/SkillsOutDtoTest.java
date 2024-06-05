package com.employee.Employee.Management.Portal.dto;

import com.employee.Employee.Management.Portal.entity.Skills;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

public class SkillsOutDtoTest {

    private SkillsOutDto dto1;
    private SkillsOutDto dto2;
    private Skills skill1;
    private Skills skill2;

    @BeforeEach
    public void setUp() {
        skill1 = new Skills();
        skill1.setId(1L);
        skill1.setSkillName("Java");

        skill2 = new Skills();
        skill2.setId(2L);
        skill2.setSkillName("Python");

        Set<Skills> skillsSet1 = new HashSet<>();
        skillsSet1.add(skill1);
        skillsSet1.add(skill2);

        Set<Skills> skillsSet2 = new HashSet<>();
        skillsSet2.add(skill1);
        skillsSet2.add(skill2);

        dto1 = new SkillsOutDto();
        dto1.setAssignedSkills(skillsSet1);

        dto2 = new SkillsOutDto();
        dto2.setAssignedSkills(skillsSet2);
    }

    @Test
    public void testGetSetAssignedSkills() {
        SkillsOutDto dto = new SkillsOutDto();
        Set<Skills> skillsSet = new HashSet<>();
        skillsSet.add(skill1);
        skillsSet.add(skill2);

        dto.setAssignedSkills(skillsSet);
        assertEquals(skillsSet, dto.getAssignedSkills());
    }

    @Test
    public void testHashCode() {
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testEquals() {
        assertEquals(dto1, dto2);

        SkillsOutDto dto3 = new SkillsOutDto();
        Set<Skills> skillsSet = new HashSet<>();
        skillsSet.add(skill1);
        dto3.setAssignedSkills(skillsSet);

        assertNotEquals(dto1, dto3);
    }

    @Test
    public void testToString() {
        String expectedString = "SkillsOutDto(assignedSkills=" + dto1.getAssignedSkills().toString() + ")";
        assertEquals(expectedString, dto1.toString());
    }
}
