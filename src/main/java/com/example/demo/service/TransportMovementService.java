package com.example.demo.service;

import com.example.demo.dto.TransportMovementDTO;
import com.example.demo.models.productsdelivery.TransportMovement;

import java.util.List;

public interface TransportMovementService {

    TransportMovementDTO findById(Long id);
    TransportMovementDTO save(TransportMovement transportMovement);
    Long delete(Long id);
    TransportMovementDTO update(TransportMovement transportMovement);
    List<TransportMovementDTO> findAll();


}
