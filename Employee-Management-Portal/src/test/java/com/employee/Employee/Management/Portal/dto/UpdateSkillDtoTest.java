package com.employee.Employee.Management.Portal.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

public class UpdateSkillDtoTest {

    private UpdateSkillDto dto1;
    private UpdateSkillDto dto2;
    private Set<Long> skillIdsToAdd1;
    private Set<Long> skillIdsToRemove1;
    private Set<Long> skillIdsToAdd2;
    private Set<Long> skillIdsToRemove2;

    @BeforeEach
    public void setUp() {
        skillIdsToAdd1 = new HashSet<>();
        skillIdsToAdd1.add(1L);
        skillIdsToAdd1.add(2L);

        skillIdsToRemove1 = new HashSet<>();
        skillIdsToRemove1.add(3L);
        skillIdsToRemove1.add(4L);

        skillIdsToAdd2 = new HashSet<>(skillIdsToAdd1);
        skillIdsToRemove2 = new HashSet<>(skillIdsToRemove1);

        dto1 = new UpdateSkillDto();
        dto1.setSkillIdsToAdd(skillIdsToAdd1);
        dto1.setSkillIdsToRemove(skillIdsToRemove1);

        dto2 = new UpdateSkillDto();
        dto2.setSkillIdsToAdd(skillIdsToAdd2);
        dto2.setSkillIdsToRemove(skillIdsToRemove2);
    }

    @Test
    public void testGetSetSkillIdsToAdd() {
        UpdateSkillDto dto = new UpdateSkillDto();
        Set<Long> skillIdsToAdd = new HashSet<>();
        skillIdsToAdd.add(1L);
        skillIdsToAdd.add(2L);

        dto.setSkillIdsToAdd(skillIdsToAdd);
        assertEquals(skillIdsToAdd, dto.getSkillIdsToAdd());
    }

    @Test
    public void testGetSetSkillIdsToRemove() {
        UpdateSkillDto dto = new UpdateSkillDto();
        Set<Long> skillIdsToRemove = new HashSet<>();
        skillIdsToRemove.add(3L);
        skillIdsToRemove.add(4L);

        dto.setSkillIdsToRemove(skillIdsToRemove);
        assertEquals(skillIdsToRemove, dto.getSkillIdsToRemove());
    }

    @Test
    public void testHashCode() {
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testEquals() {
        assertEquals(dto1, dto2);

        UpdateSkillDto dto3 = new UpdateSkillDto();
        Set<Long> skillIdsToAdd = new HashSet<>();
        skillIdsToAdd.add(1L);
        dto3.setSkillIdsToAdd(skillIdsToAdd);

        Set<Long> skillIdsToRemove = new HashSet<>();
        skillIdsToRemove.add(3L);
        dto3.setSkillIdsToRemove(skillIdsToRemove);

        assertNotEquals(dto1, dto3);
    }

    @Test
    public void testToString() {
        String expectedString = "UpdateSkillDto{skillIdsToAdd=" + dto1.getSkillIdsToAdd().toString() +
                ", skillIdsToRemove=" + dto1.getSkillIdsToRemove().toString() + "}";
        assertEquals(expectedString, dto1.toString());
    }
}
