package com.example.demo.controller;

import com.example.demo.models.refugees.Refugee;
import com.example.demo.models.RefugeeRegistrationRequest;
import com.example.demo.service.BorderCrossingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/border-crossing")
public class BorderCrossingController {

    private final BorderCrossingService borderCrossingService;

    public BorderCrossingController(BorderCrossingService borderCrossingService) {
        this.borderCrossingService = borderCrossingService;
    }

    @PostMapping("/registration")
    public ResponseEntity<Refugee> registerRefugee(RefugeeRegistrationRequest request) {
        return new ResponseEntity<Refugee>(this.borderCrossingService.registerRefugee(request), HttpStatus.OK);
    }

}
