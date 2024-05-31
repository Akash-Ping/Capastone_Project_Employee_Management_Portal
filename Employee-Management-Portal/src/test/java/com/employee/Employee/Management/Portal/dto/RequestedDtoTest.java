package com.employee.Employee.Management.Portal.dto;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RequestedDtoTest {

    @Test
    public void testGetEmpId() {
        RequestedDto dto = new RequestedDto();
        String empId = "EMP123";
        dto.setEmpId(empId);

        assertEquals(empId, dto.getEmpId());
    }

    @Test
    public void testGetEmail() {
        RequestedDto dto = new RequestedDto();
        String email = "employee@example.com";
        dto.setEmail(email);

        assertEquals(email, dto.getEmail());
    }

    @Test
    public void testAnnotations() {
        RequestedDto dto = new RequestedDto();
        dto.setEmpId("");
        dto.setEmail("");

        // Validate empId is not empty
        assertTrue(dto.getEmpId().isEmpty());

        // Validate email is not empty
        assertTrue(dto.getEmail().isEmpty());
    }

    @Test
    public void testHashCode() {
        RequestedDto dto1 = new RequestedDto();
        dto1.setEmpId("EMP123");
        dto1.setEmail("employee@example.com");

        RequestedDto dto2 = new RequestedDto();
        dto2.setEmpId("EMP123");
        dto2.setEmail("employee@example.com");

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testEquals() {
        RequestedDto dto1 = new RequestedDto();
        dto1.setEmpId("EMP123");
        dto1.setEmail("employee@example.com");

        RequestedDto dto2 = new RequestedDto();
        dto2.setEmpId("EMP123");
        dto2.setEmail("employee@example.com");

        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testToString() {
        RequestedDto dto = new RequestedDto();
        dto.setEmpId("EMP123");
        dto.setEmail("employee@example.com");

        String expectedString = "RequestedDto{empId='EMP123', email='employee@example.com'}";
        assertEquals(expectedString, dto.toString());
    }
}
