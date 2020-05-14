package com.mir00r.userlogin.users.repositories;

import com.mir00r.userlogin.users.models.entites.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mir00r on 15/5/20
 * @project IntelliJ IDEA
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
