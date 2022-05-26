package com.example.demo.service;

import com.example.demo.models.refugees.Refugee;
import com.example.demo.repo.RefugeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefugeeService {

    private final RefugeeRepo refugeeRepo;

    public RefugeeService(RefugeeRepo refugeeRepo) {
        this.refugeeRepo = refugeeRepo;
    }

    public List<Refugee> findAllRefugees(){
        return this.refugeeRepo.findAll();
    }

    public Refugee saveRefugee(Refugee refugee){
        return refugeeRepo.save(refugee);
    }
}
