package com.example.demo.service;

import com.example.demo.models.Refugee;
import com.example.demo.utils.PhoneValidator;
import org.springframework.stereotype.Service;

@Service
public class RefugeeRegistrationService {

    private final PhoneValidator phoneValidator;
    private final RefugeeService refugeeService;

    public RefugeeRegistrationService(PhoneValidator phoneValidator, RefugeeService refugeeService) {
        this.phoneValidator = phoneValidator;
        this.refugeeService = refugeeService;
    }

    public Refugee refugeeRegistration(Refugee refugee){
        if(!this.phoneValidator.test(refugee.getContact().getTelephoneNumber())){
            throw new IllegalArgumentException("Phone number is not valid");
        }
        return refugeeService.create(refugee);
    }
}
