package com.example.demo.service;

import com.example.demo.models.LocalGovernment;
import com.example.demo.repo.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class LocalGovernmentService extends GenericService<LocalGovernment> {

    public LocalGovernmentService(GenericRepository<LocalGovernment> repository) {
        super(repository);
    }
}
