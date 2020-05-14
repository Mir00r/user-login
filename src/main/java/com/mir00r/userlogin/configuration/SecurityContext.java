package com.mir00r.userlogin.configuration;

import com.mir00r.userlogin.users.models.UserAuth;
import com.mir00r.userlogin.users.models.entites.User;
import com.mir00r.userlogin.users.repositories.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author mir00r on 14/5/20
 * @project IntelliJ IDEA
 */
@Component
public class SecurityContext {
    private static UserRepository userRepository;
    private static UserAuth loggedInUser;

    public SecurityContext(UserRepository userRepository) {
        SecurityContext.userRepository = userRepository;
    }


    public static void updateAuthentication(UserAuth user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public static UserAuth getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            if (authentication.getPrincipal() instanceof String) {
                if (loggedInUser == null || !authentication.getPrincipal().equals(loggedInUser.getUsername())) {
                    Optional<User> user = Optional.ofNullable(SecurityContext.userRepository.findByUsername((String) authentication.getPrincipal()));
                    if (!user.isPresent()) return null;
                    loggedInUser = new UserAuth(user.get());
                }
                return loggedInUser;
            }
            return (UserAuth) authentication.getPrincipal();
        }
        return null;
    }

    public static boolean isAuthenticated() {
        return getCurrentUser() != null;
    }

    public static String getToken() {
        String token = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            token = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
        }
        return token;
    }
}
