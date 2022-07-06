package com.example.demo.service;

import com.example.demo.models.productsdelivery.TransportMovement;

import java.util.Set;

public interface TransportMovementService {

    TransportMovement findById(Long id);
    TransportMovement save(TransportMovement transportMovement);
    Long delete(Long id);
    TransportMovement update(TransportMovement transportMovement);
    Set<TransportMovement> findAll();


}
