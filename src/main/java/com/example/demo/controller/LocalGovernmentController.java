package com.example.demo.controller;

import com.example.demo.models.LocalGovernment;
import com.example.demo.repo.GenericRepository;
import com.example.demo.repo.LocalGovernmentRepo;
import com.example.demo.service.LocalGovernmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/local-government")
public class LocalGovernmentController extends GenericController<LocalGovernment> {

    protected LocalGovernmentController(LocalGovernmentService service) {
        super(service);
    }
}
