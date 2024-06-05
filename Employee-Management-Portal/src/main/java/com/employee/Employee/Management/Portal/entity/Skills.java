package com.employee.Employee.Management.Portal.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import org.hibernate.proxy.HibernateProxy;

import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name = "skill")
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "skill_name")
    private String skillName;

    @JsonBackReference
    @ManyToMany(mappedBy = "assignedSkills")
    @ToString.Exclude
    private Set<User> users = new HashSet<>() {
    };


    public Skills() {
    super();
}


    public Skills(final String skillName) {
        this.skillName = skillName;
    }


    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(final String skillName) {
        this.skillName = skillName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(final Set<User> users) {
        this.users = users;
    }


    @Override
    public final boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o)
                .getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this)
                .getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Skills skills = (Skills) o;
        return getId() != null && Objects.equals(getId(), skills.getId());
    }


@Override
public final int hashCode() {
    return Objects.hash(id, skillName);
}

}
