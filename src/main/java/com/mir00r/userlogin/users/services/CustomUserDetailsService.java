package com.mir00r.userlogin.users.services;

import com.mir00r.userlogin.Utils.NetworkUtil;
import com.mir00r.userlogin.exceptions.UserNotFoundException;
import com.mir00r.userlogin.users.models.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author mir00r on 14/5/20
 * @project IntelliJ IDEA
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final LoginAttemptService loginAttemptService;

    @Autowired
    public CustomUserDetailsService(UserService userService, LoginAttemptService loginAttemptService) {
        this.userService = userService;
        this.loginAttemptService = loginAttemptService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // flood control
        String ip = NetworkUtil.getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }
        // end flood control
        User user;
        user = this.userService.findByUsername(username); //.orElseThrow(() -> new UserNotFoundException("User doesn't exist!"));
        if (user == null) throw new UsernameNotFoundException("User doesn't exist!");
        return (UserDetails) user;
    }
}
