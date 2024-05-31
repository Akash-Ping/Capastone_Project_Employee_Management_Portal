package com.employee.Employee.Management.Portal.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoleTest {

    @Test
    public void testEnumValues() {
        Role[] expectedRoles = Role.values();
        assertArrayEquals(expectedRoles, Role.values());
    }

    @Test
    public void testEnumValueOf() {
        assertEquals(Role.ADMIN, Role.valueOf("ADMIN"));
        assertEquals(Role.EMPLOYEE, Role.valueOf("EMPLOYEE"));
        assertEquals(Role.MANAGER, Role.valueOf("MANAGER"));
    }

    @Test
    public void testEnumToString() {
        assertEquals("ADMIN", Role.ADMIN.toString());
        assertEquals("EMPLOYEE", Role.EMPLOYEE.toString());
        assertEquals("MANAGER", Role.MANAGER.toString());
    }
}
