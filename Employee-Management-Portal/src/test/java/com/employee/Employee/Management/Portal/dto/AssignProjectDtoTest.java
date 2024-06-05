package com.employee.Employee.Management.Portal.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AssignProjectDtoTest {

    @Test
    void testGettersAndSetters() {
        AssignProjectDto assignProjectDto = new AssignProjectDto();
        assignProjectDto.setId(1L);
        assignProjectDto.setEmpId("E12345");

        assertThat(assignProjectDto.getId()).isEqualTo(1L);
        assertThat(assignProjectDto.getEmpId()).isEqualTo("E12345");
    }

    @Test
    void testEquals() {
        AssignProjectDto dto1 = new AssignProjectDto();
        dto1.setId(1L);
        dto1.setEmpId("E12345");

        AssignProjectDto dto2 = new AssignProjectDto();
        dto2.setId(1L);
        dto2.setEmpId("E12345");

        AssignProjectDto dto3 = new AssignProjectDto();
        dto3.setId(2L);
        dto3.setEmpId("E54321");

        assertThat(dto1).isEqualTo(dto2);
        assertThat(dto1).isNotEqualTo(dto3);
    }

    @Test
    void testHashCode() {
        AssignProjectDto dto1 = new AssignProjectDto();
        dto1.setId(1L);
        dto1.setEmpId("E12345");

        AssignProjectDto dto2 = new AssignProjectDto();
        dto2.setId(1L);
        dto2.setEmpId("E12345");

        AssignProjectDto dto3 = new AssignProjectDto();
        dto3.setId(2L);
        dto3.setEmpId("E54321");

        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
        assertThat(dto1.hashCode()).isNotEqualTo(dto3.hashCode());
    }

    @Test
    void testToString() {
        AssignProjectDto assignProjectDto = new AssignProjectDto();
        assignProjectDto.setId(1L);
        assignProjectDto.setEmpId("E12345");

        assertThat(assignProjectDto.toString()).isEqualTo("AssignProjectDto(id=1, empId=E12345)");
    }
}

