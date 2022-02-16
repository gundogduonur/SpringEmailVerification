package com.onurgundogdu.emailverify.controller;
import com.onurgundogdu.emailverify.request.RegistrationRequest;
import com.onurgundogdu.emailverify.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    public String register(@RequestBody RegistrationRequest request){
        return RegistrationService.register(request);
    }
}