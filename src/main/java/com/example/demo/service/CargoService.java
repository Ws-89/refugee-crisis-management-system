package com.example.demo.service;

import com.example.demo.dto.CargoDTO;
import com.example.demo.models.cargo.Cargo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CargoService {

    public CargoDTO findById(Long id);
    public CargoDTO saveCargo(Cargo cargo);
    public CargoDTO updateCargo(Cargo cargo);
    public Long deleteCargo(Long id);
    public Page<CargoDTO> findByDescriptionContaining(String description, int page, int size);
    public boolean assignProductToCargo(Long cargoId, Long productId);
    public boolean removeProductFromCargo(Long cargoId, Long productId);
}
