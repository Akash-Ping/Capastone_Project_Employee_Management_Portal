package com.employee.Employee.Management.Portal.dto;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RequestedOutDtoTest {

    @Test
    public void testIsRequested() {
        RequestedOutDto dto = new RequestedOutDto();
        dto.setRequested(true);
        assertTrue(dto.isRequested());
    }

    @Test
    public void testIsNotRequested() {
        RequestedOutDto dto = new RequestedOutDto();
        dto.setRequested(false);
        assertFalse(dto.isRequested());
    }

    @Test
    public void testHashCode() {
        RequestedOutDto dto1 = new RequestedOutDto();
        dto1.setRequested(true);

        RequestedOutDto dto2 = new RequestedOutDto();
        dto2.setRequested(true);

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testEquals() {
        RequestedOutDto dto1 = new RequestedOutDto();
        dto1.setRequested(true);

        RequestedOutDto dto2 = new RequestedOutDto();
        dto2.setRequested(true);

        RequestedOutDto dto3 = new RequestedOutDto();
        dto3.setRequested(false);

        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
    }

    @Test
    public void testToString() {
        RequestedOutDto dto = new RequestedOutDto();
        dto.setRequested(true);

        String expectedString = "RequestedOutDto{isRequested=true}";
        assertEquals(expectedString, dto.toString());
    }
}
