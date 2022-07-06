package com.example.demo.controller;

import com.example.demo.models.Refugee;
import com.example.demo.repo.RefugeeRepo;
import com.example.demo.service.RefugeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/refugees")
public class RefugeeController {


    /* METHODS
    @GetMapping("")
    public ResponseEntity<Page<T>> getPage(Pageable pageable){
        return ResponseEntity.ok(service.getPage(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.get(id));
    }

    @PutMapping("")
    public ResponseEntity<T> update(@RequestBody T updated){
        return ResponseEntity.ok(service.update(updated));
    }

    @PostMapping("")
    public ResponseEntity<T> create(@RequestBody T created){
        return ResponseEntity.ok(service.create(created));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok("OK");
    }
     */
}
