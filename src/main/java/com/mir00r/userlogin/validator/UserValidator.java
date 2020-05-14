package com.mir00r.userlogin.validator;

import com.mir00r.userlogin.users.models.entites.User;
import com.mir00r.userlogin.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author mir00r on 15/5/20
 * @project IntelliJ IDEA
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "error.user",
                    "User name has already been taken!");
        } else if (userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "error.user",
                    "Email has already been taken!");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
    }
}
