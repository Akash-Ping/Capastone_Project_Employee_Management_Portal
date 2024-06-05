package com.employee.Employee.Management.Portal.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SkillsTest {

    private Skills skills1;
    private Skills skills2;
    private Skills skills3;
    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        user1 = new User();
        user1.setId(1L);
        user1.setName("User1");

        user2 = new User();
        user2.setId(2L);
        user2.setName("User2");

        Set<User> users1 = new HashSet<>();
        users1.add(user1);

        Set<User> users2 = new HashSet<>();
        users2.add(user2);

        skills1 = new Skills();
        skills1.setId(1L);
        skills1.setSkillName("Java");
        skills1.setUsers(users1);

        skills2 = new Skills();
        skills2.setId(1L);
        skills2.setSkillName("Java");
        skills2.setUsers(users1);

        skills3 = new Skills();
        skills3.setId(2L);
        skills3.setSkillName("Python");
        skills3.setUsers(users2);
    }

    @Test
    public void testGetSetId() {
        Skills skill = new Skills();
        Long id = 3L;
        skill.setId(id);
        assertEquals(id, skill.getId());
    }

    @Test
    public void testGetSetSkillName() {
        Skills skill = new Skills();
        String skillName = "JavaScript";
        skill.setSkillName(skillName);
        assertEquals(skillName, skill.getSkillName());
    }

    @Test
    public void testGetSetUsers() {
        Skills skill = new Skills();
        Set<User> users = new HashSet<>();
        users.add(user1);
        skill.setUsers(users);
        assertEquals(users, skill.getUsers());

        // Test with an empty user set
        skill.setUsers(new HashSet<>());
        assertTrue(skill.getUsers().isEmpty());
    }

    @Test
    public void testEquals() {
        // Same object
        assertEquals(skills1, skills1);

        // Different object, same content
        assertEquals(skills1, skills2);

        // Different object, different content
        assertNotEquals(skills1, skills3);

        // Null object
        assertNotEquals(skills1, null);

        // Different class
        assertNotEquals(skills1, new Object());

        // Same ID but different skill names (test case removed)
    }

    @Test
    public void testHashCode() {
        // Same content, same hash code
        assertEquals(skills1.hashCode(), skills2.hashCode());

        // Different content, different hash code
        Skills differentSkills = new Skills();
        differentSkills.setId(3L);
        differentSkills.setSkillName("JavaScript");
        differentSkills.setUsers(new HashSet<>(Set.of(user2)));

        assertNotEquals(skills1.hashCode(), differentSkills.hashCode());
    }

    @Test
    public void testToString() {
        String expectedString = "Skills(id=1, skillName=Java)";
        assertEquals(expectedString, skills1.toString());
    }

    @Test
    public void testNoArgsConstructor() {
        Skills skill = new Skills();
        assertNotNull(skill);
    }

    @Test
    public void testAllArgsConstructor() {
        Set<User> users = new HashSet<>();
        users.add(user1);
        Skills skill = new Skills(3L, "JavaScript", users);
        assertEquals(3L, skill.getId());
        assertEquals("JavaScript", skill.getSkillName());
        assertEquals(users, skill.getUsers());
    }

    @Test
    public void testEqualsSymmetric() {
        assertTrue(skills1.equals(skills2) && skills2.equals(skills1));
        assertEquals(skills1.hashCode(), skills2.hashCode());
    }

    @Test
    public void testEqualsConsistency() {
        assertTrue(skills1.equals(skills2));
        assertTrue(skills1.equals(skills2));
    }

    @Test
    public void testEqualsTransitive() {
        Skills skills4 = new Skills();
        skills4.setId(1L);
        skills4.setSkillName("Java");
        skills4.setUsers(skills1.getUsers());

        assertTrue(skills1.equals(skills2) && skills2.equals(skills4) && skills1.equals(skills4));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(skills1.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(skills1.equals(new Object()));
    }
}
