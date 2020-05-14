package com.mir00r.userlogin.exceptions;

/**
 * @author mir00r on 14/5/20
 * @project IntelliJ IDEA
 */
public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String s) {
        super(s);
    }
}
