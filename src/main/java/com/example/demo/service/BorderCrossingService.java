package com.example.demo.service;

import com.example.demo.models.BorderCrossing;
import com.example.demo.repo.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class BorderCrossingService extends GenericService<BorderCrossing>{

    public BorderCrossingService(GenericRepository<BorderCrossing> repository) {
        super(repository);
    }
}
