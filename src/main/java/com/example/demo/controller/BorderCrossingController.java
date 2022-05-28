package com.example.demo.controller;

import com.example.demo.models.BorderCrossing;
import com.example.demo.service.BorderCrossingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/border-crossing")
public class BorderCrossingController extends GenericController<BorderCrossing> {

    protected BorderCrossingController(BorderCrossingService service) {
        super(service);
    }
}
