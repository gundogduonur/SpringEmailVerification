package com.onurgundogdu.emailverify.service;

import com.onurgundogdu.emailverify.entity.AppUser;
import com.onurgundogdu.emailverify.request.RegistrationRequest;
import com.onurgundogdu.emailverify.role.AppUserRole;
import com.onurgundogdu.emailverify.validation.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService userService;
    private final EmailValidator emailValidator;

    public  String register(RegistrationRequest request) {
        boolean isValidEmail=emailValidator.test(request.getEmail());
        if(!isValidEmail)
        {
            throw new IllegalStateException("Email not valid.");
        }
        return userService.signUpUser(new AppUser(request.getFirstname(),
                request.getLastname(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER
                ));
    }
}
