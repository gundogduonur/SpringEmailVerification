package com.onurgundogdu.emailverify.service;

import com.onurgundogdu.emailverify.entity.User;
import com.onurgundogdu.emailverify.request.RegistrationRequest;
import com.onurgundogdu.emailverify.role.UserRole;
import com.onurgundogdu.emailverify.validation.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;

    public  String register(RegistrationRequest request) {
        boolean isValidEmail=emailValidator.test(request.getEmail());
        if(!isValidEmail)
        {
            throw new IllegalStateException("Email not valid.");
        }
        return userService.signUpUser(new User(request.getFirstname(),
                request.getLastname(),
                request.getEmail(),
                request.getPassword(),
                UserRole.USER
                ));
    }
}
