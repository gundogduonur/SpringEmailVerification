package com.onurgundogdu.emailverify.service;

import com.onurgundogdu.emailverify.request.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    public  String register(RegistrationRequest request) {
        return "works";
    }
}
