package com.mir00r.userlogin.users.services;

import com.mir00r.userlogin.users.models.entites.User;

/**
 * @author mir00r on 14/5/20
 * @project IntelliJ IDEA
 */
public interface UserService {
    User findByUsername(String username);

    User findByEmail(String email);

    User save(User user);
}
