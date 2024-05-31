package com.employee.Employee.Management.Portal.dto;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class FilterDtoTest {
    @Test
    void testSetAndGetSkills() {
        FilterDto filterDto = new FilterDto();

        // Test setting and getting non-null skills
        List<String> skills = Collections.singletonList("Java");
        filterDto.setSkills(skills);
        assertThat(filterDto.getSkills()).containsExactly("Java");

        // Test setting skills to null and getting an empty list
        filterDto.setSkills(null);
        assertThat(filterDto.getSkills()).isNotNull();
        assertThat(filterDto.getSkills()).isEmpty();

        // Test setting and getting an empty list
        filterDto.setSkills(Collections.emptyList());
        assertThat(filterDto.getSkills()).isNotNull();
        assertThat(filterDto.getSkills()).isEmpty();
    }

    @Test
    void testSetAndGetChecked() {
        FilterDto filterDto = new FilterDto();

        filterDto.setChecked(true);
        assertThat(filterDto.getChecked()).isTrue();

        filterDto.setChecked(false);
        assertThat(filterDto.getChecked()).isFalse();
    }

    @Test
    void testEqualsAndHashCode() {
        FilterDto filterDto1 = new FilterDto();
        filterDto1.setSkills(Collections.singletonList("Java"));
        filterDto1.setChecked(true);

        FilterDto filterDto2 = new FilterDto();
        filterDto2.setSkills(Collections.singletonList("Java"));
        filterDto2.setChecked(true);

        assertThat(filterDto1).isEqualTo(filterDto2);
        assertThat(filterDto1.hashCode()).isEqualTo(filterDto2.hashCode());
    }

    @Test
    void testNotEqualsAndHashCode() {
        FilterDto filterDto1 = new FilterDto();
        filterDto1.setSkills(Collections.singletonList("Java"));
        filterDto1.setChecked(true);

        FilterDto filterDto2 = new FilterDto();
        filterDto2.setSkills(Collections.singletonList("Python"));
        filterDto2.setChecked(false);

        assertThat(filterDto1).isNotEqualTo(filterDto2);
        assertThat(filterDto1.hashCode()).isNotEqualTo(filterDto2.hashCode());
    }

    @Test
    void testToString() {
        FilterDto filterDto = new FilterDto();
        filterDto.setSkills(Collections.singletonList("Java"));
        filterDto.setChecked(true);

        String expectedString = "FilterDto [skills=[Java], checked=true]";
        assertThat(filterDto.toString()).isEqualTo(expectedString);
    }

    @Test
    void testEmptySkillsListToString() {
        FilterDto filterDto = new FilterDto();
        filterDto.setChecked(false);

        String expectedString = "FilterDto [skills=[], checked=false]";
        assertThat(filterDto.toString()).isEqualTo(expectedString);
    }
}

