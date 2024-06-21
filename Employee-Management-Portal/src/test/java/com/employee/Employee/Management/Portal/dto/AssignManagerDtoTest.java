package com.employee.Employee.Management.Portal.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AssignManagerDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testAssignManagerDto() {
        // Test setting and getting fields
        AssignManagerDto dto = new AssignManagerDto();
        dto.setProjectId(1L);
        dto.setManagerId("M001");

        assertEquals(1L, dto.getProjectId());
        assertEquals("M001", dto.getManagerId());
    }

    @Test
    void testValidationConstraints() {
        AssignManagerDto dto = new AssignManagerDto();

        // Test that validation constraints are enforced
        Set<ConstraintViolation<AssignManagerDto>> violations = validator.validate(dto);
        assertEquals(2, violations.size());

        // Set valid values and test again
        dto.setProjectId(1L);
        dto.setManagerId("M001");
        violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testEqualsAndHashCode() {
        AssignManagerDto dto1 = new AssignManagerDto();
        dto1.setProjectId(1L);
        dto1.setManagerId("M001");

        AssignManagerDto dto2 = new AssignManagerDto();
        dto2.setProjectId(1L);
        dto2.setManagerId("M001");

        AssignManagerDto dto3 = new AssignManagerDto();
        dto3.setProjectId(2L);
        dto3.setManagerId("M002");

        // Test equality
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);

        // Test hashcode
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    @Test
    void testToString() {
        AssignManagerDto dto = new AssignManagerDto();
        dto.setProjectId(1L);
        dto.setManagerId("M001");

        String expectedToString = "AssignManagerDto(projectId=1, managerId=M001)";
        assertEquals(expectedToString, dto.toString());
    }
}
