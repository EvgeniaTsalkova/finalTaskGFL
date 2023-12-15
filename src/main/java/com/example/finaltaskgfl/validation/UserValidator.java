package com.example.finaltaskgfl.validation;

import com.example.finaltaskgfl.models.User;
import com.example.finaltaskgfl.services.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        Optional<User> optionalUser = userService.getUserByEmail(user.getEmail());

        if (optionalUser.isPresent()) {
            errors.rejectValue("email", "", "User with the same email already exists");
        }
    }
}
