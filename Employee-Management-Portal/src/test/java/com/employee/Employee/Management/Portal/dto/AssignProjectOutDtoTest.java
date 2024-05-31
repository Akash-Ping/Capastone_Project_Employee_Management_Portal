package com.employee.Employee.Management.Portal.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AssignProjectOutDtoTest {

    @Test
    void testGettersAndSetters() {
        AssignProjectOutDto assignProjectOutDto = new AssignProjectOutDto();
        assignProjectOutDto.setId(1L);
        assignProjectOutDto.setProjectName("Project Alpha");

        assertThat(assignProjectOutDto.getId()).isEqualTo(1L);
        assertThat(assignProjectOutDto.getProjectName()).isEqualTo("Project Alpha");
    }

    @Test
    void testEquals() {
        AssignProjectOutDto dto1 = new AssignProjectOutDto();
        dto1.setId(1L);
        dto1.setProjectName("Project Alpha");

        AssignProjectOutDto dto2 = new AssignProjectOutDto();
        dto2.setId(1L);
        dto2.setProjectName("Project Alpha");

        AssignProjectOutDto dto3 = new AssignProjectOutDto();
        dto3.setId(2L);
        dto3.setProjectName("Project Beta");

        assertThat(dto1).isEqualTo(dto2);
        assertThat(dto1).isNotEqualTo(dto3);
    }

    @Test
    void testHashCode() {
        AssignProjectOutDto dto1 = new AssignProjectOutDto();
        dto1.setId(1L);
        dto1.setProjectName("Project Alpha");

        AssignProjectOutDto dto2 = new AssignProjectOutDto();
        dto2.setId(1L);
        dto2.setProjectName("Project Alpha");

        AssignProjectOutDto dto3 = new AssignProjectOutDto();
        dto3.setId(2L);
        dto3.setProjectName("Project Beta");

        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
        assertThat(dto1.hashCode()).isNotEqualTo(dto3.hashCode());
    }

    @Test
    void testToString() {
        AssignProjectOutDto assignProjectOutDto = new AssignProjectOutDto();
        assignProjectOutDto.setId(1L);
        assignProjectOutDto.setProjectName("Project Alpha");

        assertThat(assignProjectOutDto.toString()).isEqualTo("AssignProjectOutDto{id=1, projectName='Project Alpha'}");
    }
}
