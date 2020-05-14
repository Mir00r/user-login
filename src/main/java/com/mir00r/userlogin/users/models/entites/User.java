package com.mir00r.userlogin.users.models.entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mir00r.userlogin.base.entities.BaseEntity;
import com.mir00r.userlogin.users.models.UserAuth;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mir00r on 14/5/20
 * @project IntelliJ IDEA
 */
@Entity
@Table(name = "m_users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true)
    private String email;

    //    @Column(unique = true)
    private String phone;

    @Column(length = 512, nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    private int active;

    private boolean enabled = true;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    public User() {
    }

    public User(UserAuth auth) {
        if (auth == null) throw new IllegalArgumentException("User can not be null!");
        this.setId(auth.getId());
        this.firstName = auth.getFirstName();
        this.lastName = auth.getLastName();
        this.username = auth.getUsername();
        this.password = auth.getPassword();
        this.phone = auth.getPhone();
        this.email = auth.getEmail();
        this.enabled = auth.isEnabled();
        this.roles = auth.getRoles();
        this.accountNonExpired = auth.isAccountNonExpired();
        this.accountNonLocked = auth.isAccountNonLocked();
        this.credentialsNonExpired = auth.isCredentialsNonExpired();
    }

    public void grantRole(Role role) {
        if (this.roles == null)
            this.roles = new ArrayList<>();
        // check if user already has that role
        if (!hasRole(role) && !role.isAdmin())
            this.roles.add(role);
    }

    public boolean hasRole(Role role) {
        return this.roles != null && this.roles.stream().anyMatch(r -> r.isSameAs(role));
    }

    public boolean isAdmin() {
        return this.roles != null &&
                this.roles.stream().anyMatch(Role::isAdmin);
    }

    public boolean isSameUsername(String username) {
        if (username == null) return false;
        return username.trim().equals(this.username) || ("+88" + username.trim()).equals(this.username);
    }

    public String getName() {
        if (lastName == null) return firstName;
        return firstName + " " + lastName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
