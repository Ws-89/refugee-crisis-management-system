package com.example.demo.service;

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

    public List<MaterialResourceDelivery> getAllMaterialResourceDelivery() {
        return this.materialResourceDeliveryRepo.findAll();
    }
}
