package com.naat.nix.validator;

import com.naat.nix.user.controller.UserService;
import com.naat.nix.user.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate (Object o, Errors errors) {
        User user = (User) o;

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }
    }
}
