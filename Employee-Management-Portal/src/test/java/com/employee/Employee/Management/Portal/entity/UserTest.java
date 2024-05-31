package com.employee.Employee.Management.Portal.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserTest {

    private User user1;
    private User user2;
    private User user3;
    private Skills skill1;
    private Skills skill2;

    @BeforeEach
    public void setUp() {
        skill1 = new Skills();
        skill1.setId(1L);
        skill1.setSkillName("Java");

        skill2 = new Skills();
        skill2.setId(2L);
        skill2.setSkillName("Python");

        Set<Skills> skills1 = new HashSet<>();
        skills1.add(skill1);

        Set<Skills> skills2 = new HashSet<>();
        skills2.add(skill2);

        user1 = new User();
        user1.setId(1L);
        user1.setName("User1");
        user1.setContactNo("1234567890");
        user1.setDob("1990-01-01");
        user1.setDoj("2020-01-01");
        user1.setDesignation("Developer");
        user1.setEmail("user1@example.com");
        user1.setEmpId("EMP001");
        user1.setLocation("Location1");
        user1.setPassword("password1");
        user1.setRole(Role.EMPLOYEE);
        user1.setAssignedSkills(skills1);
        user1.setEmpManagerId(1L);
        user1.setEmpProjectId(1L);

        user2 = new User();
        user2.setId(1L);
        user2.setName("User1");
        user2.setContactNo("1234567890");
        user2.setDob("1990-01-01");
        user2.setDoj("2020-01-01");
        user2.setDesignation("Developer");
        user2.setEmail("user1@example.com");
        user2.setEmpId("EMP001");
        user2.setLocation("Location1");
        user2.setPassword("password1");
        user2.setRole(Role.EMPLOYEE);
        user2.setAssignedSkills(skills1);
        user2.setEmpManagerId(1L);
        user2.setEmpProjectId(1L);

        user3 = new User();
        user3.setId(2L);
        user3.setName("User2");
        user3.setContactNo("0987654321");
        user3.setDob("1991-01-01");
        user3.setDoj("2021-01-01");
        user3.setDesignation("Manager");
        user3.setEmail("user2@example.com");
        user3.setEmpId("EMP002");
        user3.setLocation("Location2");
        user3.setPassword("password2");
        user3.setRole(Role.MANAGER);
        user3.setAssignedSkills(skills2);
        user3.setEmpManagerId(2L);
        user3.setEmpProjectId(2L);
    }

    @Test
    public void testGetSetId() {
        User user = new User();
        Long id = 3L;
        user.setId(id);
        assertEquals(id, user.getId());
    }

    @Test
    public void testGetSetName() {
        User user = new User();
        String name = "User3";
        user.setName(name);
        assertEquals(name, user.getName());
    }

    @Test
    public void testGetSetContactNo() {
        User user = new User();
        String contactNo = "1122334455";
        user.setContactNo(contactNo);
        assertEquals(contactNo, user.getContactNo());
    }

    @Test
    public void testGetSetDob() {
        User user = new User();
        String dob = "1992-01-01";
        user.setDob(dob);
        assertEquals(dob, user.getDob());
    }

    @Test
    public void testGetSetDoj() {
        User user = new User();
        String doj = "2022-01-01";
        user.setDoj(doj);
        assertEquals(doj, user.getDoj());
    }

    @Test
    public void testGetSetDesignation() {
        User user = new User();
        String designation = "Tester";
        user.setDesignation(designation);
        assertEquals(designation, user.getDesignation());
    }

    @Test
    public void testGetSetEmail() {
        User user = new User();
        String email = "user3@example.com";
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testGetSetEmpId() {
        User user = new User();
        String empId = "EMP003";
        user.setEmpId(empId);
        assertEquals(empId, user.getEmpId());
    }

    @Test
    public void testGetSetLocation() {
        User user = new User();
        String location = "Location3";
        user.setLocation(location);
        assertEquals(location, user.getLocation());
    }

    @Test
    public void testGetSetPassword() {
        User user = new User();
        String password = "password3";
        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testGetSetRole() {
        User user = new User();
        Role role = Role.ADMIN;
        user.setRole(role);
        assertEquals(role, user.getRole());
    }

    @Test
    public void testGetSetAssignedSkills() {
        User user = new User();
        Set<Skills> skills = new HashSet<>();
        skills.add(skill1);
        user.setAssignedSkills(skills);
        assertEquals(skills, user.getAssignedSkills());

        // Test with an empty skills set
        user.setAssignedSkills(new HashSet<>());
        assertTrue(user.getAssignedSkills().isEmpty());
    }

    @Test
    public void testGetSetEmpManagerId() {
        User user = new User();
        Long empManagerId = 4L;
        user.setEmpManagerId(empManagerId);
        assertEquals(empManagerId, user.getEmpManagerId());
    }

    @Test
    public void testGetSetEmpProjectId() {
        User user = new User();
        Long empProjectId = 5L;
        user.setEmpProjectId(empProjectId);
        assertEquals(empProjectId, user.getEmpProjectId());
    }

    @Test
    public void testEquals() {
        // Same object
        assertEquals(user1, user1);

        // Different object, same content
        assertEquals(user1, user2);

        // Different object, different content
        assertNotEquals(user1, user3);

        // Null object
        assertNotEquals(user1, null);

        // Different class
        assertNotEquals(user1, new Object());

        // Same ID but different other fields
        User user4 = new User();
        user4.setId(1L);
        user4.setName("Different");
        user4.setContactNo("Different");
        user4.setDob("Different");
        user4.setDoj("Different");
        user4.setDesignation("Different");
        user4.setEmail("Different");
        user4.setEmpId("Different");
        user4.setLocation("Different");
        user4.setPassword("Different");
        user4.setRole(Role.ADMIN);
        user4.setAssignedSkills(new HashSet<>());
        user4.setEmpManagerId(3L);
        user4.setEmpProjectId(3L);

        assertEquals(user1, user4);  // They should still be equal because they have the same ID
    }

    @Test
    public void testHashCode() {
        // Same content, same hash code
        assertEquals(user1.hashCode(), user2.hashCode());

        // Different content, different hash code
        User differentUser = new User();
        differentUser.setId(3L);
        differentUser.setName("Different");
        differentUser.setContactNo("Different");
        differentUser.setDob("Different");
        differentUser.setDoj("Different");
        differentUser.setDesignation("Different");
        differentUser.setEmail("Different");
        differentUser.setEmpId("Different");
        differentUser.setLocation("Different");
        differentUser.setPassword("Different");
        differentUser.setRole(Role.ADMIN);
        differentUser.setAssignedSkills(new HashSet<>());
        differentUser.setEmpManagerId(3L);
        differentUser.setEmpProjectId(3L);

        assertNotEquals(user1.hashCode(), differentUser.hashCode());


    // Ensure hash codes are generated based on all relevant fields
        assertEquals(user1.hashCode(), user1.hashCode());
    }



    @Test
    public void testToString() {
        String expectedString = "User(id=1, name=User1, contactNo=1234567890, dob=1990-01-01, doj=2020-01-01, designation=Developer, email=user1@example.com, empId=EMP001, location=Location1, password=password1, role=EMPLOYEE, assignedSkills=[Skills(id=1, skillName=Java)], empManagerId=1, empProjectId=1)";
        assertEquals(expectedString, user1.toString());
    }

    @Test
    public void testNoArgsConstructor() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    public void testAllArgsConstructor() {
        Set<Skills> skills = new HashSet<>();
        skills.add(skill1);
        User user = new User(3L, "User3", "1122334455", "1992-01-01", "2022-01-01", "Tester", "user3@example.com", "EMP003", "Location3", "password3", Role.ADMIN, skills, 4L, 5L, null);
        assertEquals(3L, user.getId());
        assertEquals("User3", user.getName());
        assertEquals("1122334455", user.getContactNo());
        assertEquals("1992-01-01", user.getDob());
        assertEquals("2022-01-01", user.getDoj());
        assertEquals("Tester", user.getDesignation());
        assertEquals("user3@example.com", user.getEmail());
        assertEquals("EMP003", user.getEmpId());
        assertEquals("Location3", user.getLocation());
        assertEquals("password3", user.getPassword());
        assertEquals(Role.ADMIN, user.getRole());
        assertEquals(skills, user.getAssignedSkills());
        assertEquals(4L, user.getEmpManagerId());
        assertEquals(5L, user.getEmpProjectId());
    }

    @Test
    public void testEqualsSymmetric() {
        assertTrue(user1.equals(user2) && user2.equals(user1));
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    public void testEqualsConsistency() {
        assertTrue(user1.equals(user2));
        assertTrue(user1.equals(user2));
    }

    @Test
    public void testEqualsTransitive() {
        User user4 = new User();
        user4.setId(1L);
        user4.setName("User1");
        user4.setContactNo("1234567890");
        user4.setDob("1990-01-01");
        user4.setDoj("2020-01-01");
        user4.setDesignation("Developer");
        user4.setEmail("user1@example.com");
        user4.setEmpId("EMP001");
        user4.setLocation("Location1");
        user4.setPassword("password1");
        user4.setRole(Role.EMPLOYEE);
        user4.setAssignedSkills(new HashSet<>(Set.of(skill1)));
        user4.setEmpManagerId(1L);
        user4.setEmpProjectId(1L);

        assertTrue(user1.equals(user2) && user2.equals(user4) && user1.equals(user4));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(user1.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(user1.equals(new Object()));
    }
}
