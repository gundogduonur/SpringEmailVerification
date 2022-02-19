package com.onurgundogdu.emailverify.service;

import com.onurgundogdu.emailverify.email.EmailSender;
import com.onurgundogdu.emailverify.entity.ConfirmationToken;
import com.onurgundogdu.emailverify.entity.User;
import com.onurgundogdu.emailverify.request.RegistrationRequest;
import com.onurgundogdu.emailverify.role.UserRole;
import com.onurgundogdu.emailverify.validation.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;

    public  String register(RegistrationRequest request) {
        boolean isValidEmail=emailValidator.test(request.getEmail());
        if(!isValidEmail)
        {
            throw new IllegalStateException("Email not valid.");
        }
        return appUserService.signUpUser(new User(request.getFirstname(),
                request.getLastname(),
                request.getEmail(),
                request.getPassword(),
                UserRole.USER
                ));
        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
        emailSender.send(
                request.getEmail(),
                buildEmail(request.getFirstName(), link));

        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail());
        return "confirmed";
    }
}
