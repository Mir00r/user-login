package com.mir00r.userlogin.listeners;

import com.mir00r.userlogin.Utils.NetworkUtil;
import com.mir00r.userlogin.users.services.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

/**
 * @author mir00r on 14/5/20
 * @project IntelliJ IDEA
 */
@Component
public class AuthenticationFailureEventListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private final LoginAttemptService loginAttemptService;

    @Autowired
    public AuthenticationFailureEventListener(LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }

    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        loginAttemptService.loginFailed(NetworkUtil.getClientIP());
    }
}
