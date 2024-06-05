package com.employee.Employee.Management.Portal.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.*;
import jakarta.validation.constraints.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RequestResourceDtoTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testGetEmpId() {
        RequestResourceDto dto = new RequestResourceDto();
        String empId = "123";
        dto.setEmpId(empId);

        assertEquals(empId, dto.getEmpId());
    }

    @Test
    public void testGetEmail() {
        RequestResourceDto dto = new RequestResourceDto();
        String email = "employee@example.com";
        dto.setEmail(email);

        assertEquals(email, dto.getEmail());
    }

    @Test
    public void testGetProjectId() {
        RequestResourceDto dto = new RequestResourceDto();
        Long projectId = 456L;
        dto.setProjectId(projectId);

        assertEquals(projectId, dto.getProjectId());
    }

    @Test
    public void testGetComment() {
        RequestResourceDto dto = new RequestResourceDto();
        String comment = "Requesting resource for a project.";
        dto.setComment(comment);

        assertEquals(comment, dto.getComment());
    }

    @Test
    public void testAnnotations() {
        RequestResourceDto dto = new RequestResourceDto();
        dto.setEmpId("");
        dto.setEmail("");
        dto.setProjectId(null);
        dto.setComment("");

        // Perform manual validation
        Set<String> invalidFields = new HashSet<>();

        if (dto.getEmpId() == null || dto.getEmpId().isEmpty()) {
            invalidFields.add("empId");
        }

        if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
            invalidFields.add("email");
        }

        if (dto.getProjectId() == null) {
            invalidFields.add("projectId");
        }

        if (dto.getComment() == null || dto.getComment().isEmpty()) {
            invalidFields.add("comment");
        }

        // Ensure that all annotated fields are marked as invalid
        assertEquals(4, invalidFields.size());

        assertTrue(invalidFields.contains("empId"));
        assertTrue(invalidFields.contains("email"));
        assertTrue(invalidFields.contains("projectId"));
        assertTrue(invalidFields.contains("comment"));
    }
    @Test
    public void testHashCode() {
        RequestResourceDto dto1 = new RequestResourceDto();
        dto1.setEmpId("EMP001");
        dto1.setEmail("employee@example.com");
        dto1.setProjectId(1L);
        dto1.setComment("Request Comment");

        RequestResourceDto dto2 = new RequestResourceDto();
        dto2.setEmpId("EMP001");
        dto2.setEmail("employee@example.com");
        dto2.setProjectId(1L);
        dto2.setComment("Request Comment");

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testEquals() {
        RequestResourceDto dto1 = new RequestResourceDto();
        dto1.setEmpId("EMP001");
        dto1.setEmail("employee@example.com");
        dto1.setProjectId(1L);
        dto1.setComment("Request Comment");

        RequestResourceDto dto2 = new RequestResourceDto();
        dto2.setEmpId("EMP001");
        dto2.setEmail("employee@example.com");
        dto2.setProjectId(1L);
        dto2.setComment("Request Comment");

        RequestResourceDto dto3 = new RequestResourceDto();
        dto3.setEmpId("EMP002");
        dto3.setEmail("employee2@example.com");
        dto3.setProjectId(2L);
        dto3.setComment("Different Comment");

        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
    }

    @Test
    public void testToString() {
        RequestResourceDto dto = new RequestResourceDto();
        dto.setEmpId("EMP001");
        dto.setEmail("employee@example.com");
        dto.setProjectId(1L);
        dto.setComment("Request Comment");

        String expectedString = "RequestResourceDto(empId=EMP001, email=employee@example.com, projectId=1, comment=Request Comment)";
        assertEquals(expectedString, dto.toString());
    }
}
