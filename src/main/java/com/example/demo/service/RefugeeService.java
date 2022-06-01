package com.example.demo.service;

import com.example.demo.models.Refugee;
import com.example.demo.repo.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class RefugeeService extends  GenericService<Refugee>{

    public RefugeeService(GenericRepository<Refugee> repository) {
        super(repository);
    }

}
