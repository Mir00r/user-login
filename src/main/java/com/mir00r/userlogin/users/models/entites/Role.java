package com.mir00r.userlogin.users.models.entites;

import com.mir00r.userlogin.Utils.Constant;
import com.mir00r.userlogin.base.entities.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * @author mir00r on 14/5/20
 * @project IntelliJ IDEA
 */
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private List<Privilege> privileges;

    @Column(nullable = false)
    private Boolean restricted = true;

    public Boolean isAdmin() {
        return privileges != null && this.privileges.stream().anyMatch(Constant.Privileges.ADMINISTRATION.name()::equals);
    }

    public Boolean isSameAs(Role role) {
        return this.getId().equals(role.getId());
    }

    public Boolean hasPrivileges(Long privilegeId) {
        return privileges != null && privilegeId.equals(privileges.get(0).getId());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getRestricted() {
        return restricted;
    }

    public void setRestricted(Boolean restricted) {
        this.restricted = restricted;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
}
