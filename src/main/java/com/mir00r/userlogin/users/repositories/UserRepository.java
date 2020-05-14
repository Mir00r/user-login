package com.mir00r.userlogin.users.repositories;

import com.mir00r.userlogin.users.models.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mir00r on 14/5/20
 * @project IntelliJ IDEA
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);
}
