package com.example.demo.service;

import com.example.demo.models.lodging.Lodging;
import com.example.demo.repo.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class LodgingService extends GenericService<Lodging> {

    public LodgingService(GenericRepository<Lodging> repository) {
        super(repository);
    }
}
