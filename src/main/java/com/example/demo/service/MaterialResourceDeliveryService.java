package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.models.materialResourceDelivery.MaterialResourceDelivery;


import com.example.demo.repo.MaterialResourceDeliveryRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialResourceDeliveryService {

    private final MaterialResourceDeliveryRepo materialResourceDeliveryRepo;

    public MaterialResourceDeliveryService(MaterialResourceDeliveryRepo materialResourceDeliveryRepo) {
        this.materialResourceDeliveryRepo = materialResourceDeliveryRepo;
    }

    public List<MaterialResourceDelivery> findAllMaterialResourceDelivery() {
        return this.materialResourceDeliveryRepo.findAll();
    }

    public MaterialResourceDelivery findById(Long id){
        return materialResourceDeliveryRepo.findById(id).orElseThrow(() -> new NotFoundException("Oops, something went wrong"));
    }

    public MaterialResourceDelivery sendDelivery(MaterialResourceDelivery materialResourceDelivery){
        return this.materialResourceDeliveryRepo.save(materialResourceDelivery);
    }

    public MaterialResourceDelivery nextState(Long id){
        MaterialResourceDelivery delivery = materialResourceDeliveryRepo.findById(id).orElseThrow(() -> new NotFoundException("Oops, something went wrong"));
        delivery.nextState();
        System.out.println(delivery.getStateName());
        return materialResourceDeliveryRepo.save(delivery);
    }

    public MaterialResourceDelivery prevState(Long id){
        MaterialResourceDelivery delivery = materialResourceDeliveryRepo.findById(id).orElseThrow(() -> new NotFoundException("Oops, something went wrong"));
        delivery.prevState();
        return materialResourceDeliveryRepo.save(delivery);
    }
}
