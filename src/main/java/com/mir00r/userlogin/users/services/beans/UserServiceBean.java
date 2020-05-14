package com.mir00r.userlogin.users.services.beans;

import com.mir00r.userlogin.Utils.Constant;
import com.mir00r.userlogin.users.models.entites.Role;
import com.mir00r.userlogin.users.models.entites.User;
import com.mir00r.userlogin.users.repositories.RoleRepository;
import com.mir00r.userlogin.users.repositories.UserRepository;
import com.mir00r.userlogin.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mir00r on 14/5/20
 * @project IntelliJ IDEA
 */
@Service
public class UserServiceBean implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        user.setRoles(getRols());
        user.setActive(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    private List<Role> getRols() {
        List<Role> roleList = new ArrayList<>();
        Role role = this.roleRepository.findByName(Constant.ROLE_TYPE.admin.name());
        roleList.add(role);
        return roleList;
    }

}
