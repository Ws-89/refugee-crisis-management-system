package com.example.demo.service;

import com.example.demo.models.lodging.Lodging;
import org.springframework.stereotype.Service;

@Service
public class LodgingRegistrationService {

    private final LodgingService lodgingService;

    public LodgingRegistrationService(LodgingService lodgingService) {
        this.lodgingService = lodgingService;
    }

    public Lodging lodgingRegistration(Lodging lodging){
        return this.lodgingService.create(lodging);
    }

    public Lodging lodgingRegistrationForLocalGovernment(Lodging lodging){
        return this.lodgingService.create(lodging);
    }
}
