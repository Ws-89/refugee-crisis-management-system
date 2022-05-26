package com.example.demo.controller;

import com.example.demo.models.refugees.Refugee;
import com.example.demo.service.RefugeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/refugees")
public class RefugeeController {

    private final RefugeeService refugeeService;

    public RefugeeController(RefugeeService refugeeService) {
        this.refugeeService = refugeeService;
    }

    @GetMapping
    public ResponseEntity<List<Refugee>> getRefugees() {
        return new ResponseEntity<List<Refugee>>(this.refugeeService.findAllRefugees(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Refugee> saveRefugee(@RequestBody Refugee refugee){
        return new ResponseEntity<>(this.refugeeService.saveRefugee(refugee), HttpStatus.OK);
    }
}
