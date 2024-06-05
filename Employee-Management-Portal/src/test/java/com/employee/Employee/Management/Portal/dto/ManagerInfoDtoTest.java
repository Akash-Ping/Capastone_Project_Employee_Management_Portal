package com.employee.Employee.Management.Portal.dto;

import com.employee.Employee.Management.Portal.dto.ManagerInfoDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ManagerInfoDtoTest {

    @Test
    void testEquals() {
        // Test equality when all fields are the same
        ManagerInfoDto manager1 = new ManagerInfoDto();
        manager1.setId(1L);
        manager1.setManagerName("John Doe");
        manager1.setManagerEmployeeId("EMP001");

        ManagerInfoDto manager2 = new ManagerInfoDto();
        manager2.setId(1L);
        manager2.setManagerName("John Doe");
        manager2.setManagerEmployeeId("EMP001");

        assertEquals(manager1, manager2);

        // Test equality when only some fields are the same
        ManagerInfoDto manager3 = new ManagerInfoDto();
        manager3.setId(1L);
        manager3.setManagerName("John Doe");
        manager3.setManagerEmployeeId("EMP001");

        ManagerInfoDto manager4 = new ManagerInfoDto();
        manager4.setId(1L);
        manager4.setManagerName("Jane Doe");
        manager4.setManagerEmployeeId("EMP002");

        assertNotEquals(manager3, manager4);
    }

    @Test
    void testHashCode() {
        // Test hash code consistency
        ManagerInfoDto manager1 = new ManagerInfoDto();
        manager1.setId(1L);
        manager1.setManagerName("John Doe");
        manager1.setManagerEmployeeId("EMP001");

        ManagerInfoDto manager2 = new ManagerInfoDto();
        manager2.setId(1L);
        manager2.setManagerName("John Doe");
        manager2.setManagerEmployeeId("EMP001");

        assertEquals(manager1.hashCode(), manager2.hashCode());

        // Test hash code equality when only some fields are the same
        ManagerInfoDto manager3 = new ManagerInfoDto();
        manager3.setId(1L);
        manager3.setManagerName("John Doe");
        manager3.setManagerEmployeeId("EMP001");

        ManagerInfoDto manager4 = new ManagerInfoDto();
        manager4.setId(1L);
        manager4.setManagerName("Jane Doe");
        manager4.setManagerEmployeeId("EMP002");

        assertNotEquals(manager3.hashCode(), manager4.hashCode());
    }

    @Test
    void testToString() {
        ManagerInfoDto manager = new ManagerInfoDto();
        manager.setId(1L);
        manager.setManagerName("John Doe");
        manager.setManagerEmployeeId("EMP001");

        String expectedString = "ManagerInfoDto{id=1, managerName='John Doe', managerEmployeeId='EMP001'}";
        assertEquals(expectedString, manager.toString());
    }

    @Test
    void testDefaultConstructor() {
        ManagerInfoDto manager = new ManagerInfoDto();
        assertNull(manager.getId());
        assertNull(manager.getManagerName());
        assertNull(manager.getManagerEmployeeId());
    }
}
