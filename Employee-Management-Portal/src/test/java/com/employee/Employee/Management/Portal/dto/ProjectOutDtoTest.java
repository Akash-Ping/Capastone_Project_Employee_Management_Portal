
package com.employee.Employee.Management.Portal.dto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectOutDtoTest {

    @Test
    public void testGetterSetter() {
        ProjectOutDto projectOutDto = new ProjectOutDto();

        Long id = 1L;
        String projectName = "Project A";
        String manager = "Manager Name";
        String startDate = "2023-08-31";
        String description = "Description of Project A";
        List<String> teamMembers = Arrays.asList("Member1", "Member2");

        projectOutDto.setId(id);
        projectOutDto.setProjectName(projectName);
        projectOutDto.setManager(manager);
        projectOutDto.setStartDate(startDate);
        projectOutDto.setDescription(description);
        projectOutDto.setTeamMembers(teamMembers);

        assertEquals(id, projectOutDto.getId());
        assertEquals(projectName, projectOutDto.getProjectName());
        assertEquals(manager, projectOutDto.getManager());
        assertEquals(startDate, projectOutDto.getStartDate());
        assertEquals(description, projectOutDto.getDescription());
        assertEquals(teamMembers, projectOutDto.getTeamMembers());
    }

    @Test
    public void testEquals() {
        ProjectOutDto projectOutDto1 = new ProjectOutDto();
        projectOutDto1.setId(1L);
        projectOutDto1.setProjectName("Project A");
        projectOutDto1.setManager("Manager Name");
        projectOutDto1.setStartDate("2023-08-31");
        projectOutDto1.setDescription("Description of Project A");
        projectOutDto1.setTeamMembers(Arrays.asList("Member1", "Member2"));

        ProjectOutDto projectOutDto2 = new ProjectOutDto();
        projectOutDto2.setId(1L);
        projectOutDto2.setProjectName("Project A");
        projectOutDto2.setManager("Manager Name");
        projectOutDto2.setStartDate("2023-08-31");
        projectOutDto2.setDescription("Description of Project A");
        projectOutDto2.setTeamMembers(Arrays.asList("Member1", "Member2"));

        ProjectOutDto projectOutDto3 = new ProjectOutDto();
        projectOutDto3.setId(2L);
        projectOutDto3.setProjectName("Project B");
        projectOutDto3.setManager("Another Manager");
        projectOutDto3.setStartDate("2023-09-01");
        projectOutDto3.setDescription("Description of Project B");
        projectOutDto3.setTeamMembers(Arrays.asList("Member3", "Member4"));

        assertEquals(projectOutDto1, projectOutDto2);
        assertNotEquals(projectOutDto1, projectOutDto3);

        assertTrue(projectOutDto1.equals(projectOutDto2));
        assertFalse(projectOutDto1.equals(projectOutDto3));

        assertFalse(projectOutDto1.equals(null));
        assertFalse(projectOutDto1.equals(new Object()));
    }

    @Test
    public void testHashCode() {
        ProjectOutDto projectOutDto1 = new ProjectOutDto();
        projectOutDto1.setId(1L);
        projectOutDto1.setProjectName("Project A");
        projectOutDto1.setManager("Manager Name");
        projectOutDto1.setStartDate("2023-08-31");
        projectOutDto1.setDescription("Description of Project A");
        projectOutDto1.setTeamMembers(Arrays.asList("Member1", "Member2"));

        ProjectOutDto projectOutDto2 = new ProjectOutDto();
        projectOutDto2.setId(1L);
        projectOutDto2.setProjectName("Project A");
        projectOutDto2.setManager("Manager Name");
        projectOutDto2.setStartDate("2023-08-31");
        projectOutDto2.setDescription("Description of Project A");
        projectOutDto2.setTeamMembers(Arrays.asList("Member1", "Member2"));

        ProjectOutDto projectOutDto3 = new ProjectOutDto();
        projectOutDto3.setId(2L);
        projectOutDto3.setProjectName("Project B");
        projectOutDto3.setManager("Another Manager");
        projectOutDto3.setStartDate("2023-09-01");
        projectOutDto3.setDescription("Description of Project B");
        projectOutDto3.setTeamMembers(Arrays.asList("Member3", "Member4"));

        assertEquals(projectOutDto1.hashCode(), projectOutDto2.hashCode());
        assertNotEquals(projectOutDto1.hashCode(), projectOutDto3.hashCode());
    }

    @Test
    public void testToString() {
        ProjectOutDto projectOutDto = new ProjectOutDto();
        projectOutDto.setId(1L);
        projectOutDto.setProjectName("Project A");
        projectOutDto.setManager("Manager Name");
        projectOutDto.setStartDate("2023-08-31");
        projectOutDto.setDescription("Description of Project A");
        projectOutDto.setTeamMembers(Arrays.asList("Member1", "Member2"));

        String expectedString = "ProjectOutDto(id=1, projectName=Project A, manager=Manager Name, startDate=2023-08-31, description=Description of Project A, teamMembers=[Member1, Member2])";
        assertEquals(expectedString, projectOutDto.toString());
    }

    @Test
    public void testCustomValidation() {
        ProjectOutDto projectOutDto = new ProjectOutDto();
        projectOutDto.setProjectName("");
        projectOutDto.setManager("");
        projectOutDto.setStartDate("");
        projectOutDto.setDescription("");
        projectOutDto.setTeamMembers(null);

        assertFalse(isValidProjectOutDto(projectOutDto));
    }

    private boolean isValidProjectOutDto(ProjectOutDto dto) {
        if (dto.getProjectName() == null || dto.getProjectName().isEmpty()) {
            return false;
        }
        if (dto.getManager() == null || dto.getManager().isEmpty()) {
            return false;
        }
        if (dto.getStartDate() == null || dto.getStartDate().isEmpty()) {
            return false;
        }
        if (dto.getDescription() == null || dto.getDescription().isEmpty()) {
            return false;
        }
        if (dto.getTeamMembers() == null) {
            return false;
        }
        return true;
    }

    @Test
    public void testCustomValidationWithValues() {
        ProjectOutDto projectOutDto = new ProjectOutDto();
        projectOutDto.setProjectName("Project A");
        projectOutDto.setManager("Manager Name");
        projectOutDto.setStartDate("2023-08-31");
        projectOutDto.setDescription("Description of Project A");
        projectOutDto.setTeamMembers(Arrays.asList("Member1", "Member2"));

        assertTrue(isValidProjectOutDto(projectOutDto));
    }
}
