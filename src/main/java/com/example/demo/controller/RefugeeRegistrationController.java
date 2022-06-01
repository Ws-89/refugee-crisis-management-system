package com.example.demo.controller;

import com.example.demo.models.Refugee;
import com.example.demo.service.RefugeeRegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/refugee-registration")
public class RefugeeRegistrationController {

    private final RefugeeRegistrationService refugeeRegistrationService;

    public RefugeeRegistrationController(RefugeeRegistrationService refugeeRegistrationService) {
        this.refugeeRegistrationService = refugeeRegistrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<Refugee> refugeeRegistration(Refugee refugee){
        return ResponseEntity.ok(this.refugeeRegistrationService.refugeeRegistration(refugee));
    }
}
