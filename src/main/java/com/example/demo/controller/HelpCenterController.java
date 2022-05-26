package com.example.demo.controller;

import com.example.demo.models.HelpCenter;
import com.example.demo.models.HelpCenterRequest;
import com.example.demo.service.HelpCenterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController("/help-center")
public class HelpCenterController {

    private final HelpCenterService helpCenterService;

    public HelpCenterController(HelpCenterService helpCenterService) {
        this.helpCenterService = helpCenterService;
    }

    @GetMapping("/list")
    public ResponseEntity<Collection<HelpCenter>> getAllHelpCenters() {
        return new ResponseEntity<Collection<HelpCenter>>(this.helpCenterService.getAllHelpCenter(), HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<HelpCenter> getHelpCenterById(@PathVariable("id") long id){
        return new ResponseEntity<HelpCenter>(this.helpCenterService.getHelpCenterById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<HelpCenter> saveHelpCenter(HelpCenterRequest request){
        return new ResponseEntity<HelpCenter>(this.helpCenterService.saveHelpCenter(request), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<HelpCenter> updateHelpCenter(HelpCenter helpCenter){
        return new ResponseEntity<HelpCenter>(this.helpCenterService.updateHelpCenter(helpCenter), HttpStatus.OK);
    }
}
