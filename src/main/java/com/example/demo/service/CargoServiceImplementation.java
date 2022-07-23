package com.example.demo.service;

import com.example.demo.dto.CargoDTO;
import com.example.demo.mappers.CargoMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.products.*;
import com.example.demo.models.cargo.*;
import com.example.demo.repo.AddressRepository;
import com.example.demo.repo.CargoRepository;
import com.example.demo.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CargoServiceImplementation implements CargoService {

    private final CargoRepository cargoRepository;
    private final ProductRepository productRepository;
    private final AddressRepository addressRepository;

    @Override
    public CargoDTO findById(Long id) {
        return cargoRepository.findById(id)
                .map(p -> CargoMapper.INSTANCE.entityToDTO(p))
                .orElseThrow(() -> new NotFoundException(String.format("Cargo with id %s not found", id)));
    }

    @Override
    @Transactional
    public CargoDTO saveCargo(Cargo source){

        Address destinationAddress = addressRepository.findById(source.getDeliverySpecification()
                .getDeliveryAddress().getAddressId()).orElseThrow(()-> new NotFoundException("Addres not found"));

        Address startingAddress = addressRepository.findById(source.getStartingAddress().getAddressId())
                .orElseThrow(()-> new NotFoundException("Addres not found"));

        DeliverySpecification deliverySpecification = source.getDeliverySpecification();
        deliverySpecification.setDeliveryAddress(destinationAddress);

        Cargo cargo = Cargo.builder()
                .deliveryHistory(source.getDeliveryHistory())
                .deliverySpecification(deliverySpecification)
                .startingAddress(startingAddress)
                .description(source.getDescription())
                .totalWeight(source.getTotalWeight())
                .status(source.getStatus())
                .build();

        return CargoMapper.INSTANCE.entityToDTO(cargoRepository.save(cargo));
    }

    @Transactional
    public boolean assignProductToCargo(Long cargoId, Long productId){
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
        Cargo cargo = cargoRepository.findById(cargoId).orElseThrow(() -> new NotFoundException("Cargo not found"));

        product.setReserved(Status.Reserved);
        cargo.addProduct(product);
        productRepository.save(product);
        return true;
    }

    @Transactional
    public boolean removeProductFromCargo(Long cargoId, Long productId){
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
        Cargo cargo = cargoRepository.findById(cargoId).orElseThrow(() -> new NotFoundException("Cargo not found"));

        product.setReserved(Status.Available);
        cargo.removeProduct(product);
        productRepository.save(product);
        return true;
    }

    public boolean finishCargoCompletion(Long cargoId){
        Cargo cargo = cargoRepository.findById(cargoId).orElseThrow(() -> new NotFoundException("Cargo not found"));
        cargo.setStatus(Status.Reserved);
        cargoRepository.save(cargo);
        return true;
    }

    @Override
    @Transactional
    public CargoDTO updateCargo(Cargo cargo) {
        Cargo result = cargoRepository.findById(cargo.getCargoId())
                .map(p -> {
                    Cargo newCargo = p;
                    newCargo.setDescription(cargo.getDescription());
                    newCargo.setTotalWeight(cargo.getTotalWeight());
                    newCargo.setStatus(cargo.getStatus());
                    newCargo.setDeliveryHistory(cargo.getDeliveryHistory());
                    newCargo.setStartingAddress(cargo.getStartingAddress());
                    return newCargo;
                })
                .orElseThrow(() -> new NotFoundException(String.format("Cargo with id %s not found", cargo.getCargoId())));
        return CargoMapper.INSTANCE.entityToDTO(cargoRepository.save(result));
    }

    @Override
    public Long deleteCargo(Long id) {
        return cargoRepository.findById(id)
                .map(p -> {
                    cargoRepository.delete(p);
                    return p.getCargoId();
                })
                .orElseThrow(() -> new NotFoundException(String.format("Cargo with id %s not found", id)));
    }

    @Override
    public Page<CargoDTO> findByDescriptionContaining(String description, int page, int size) {
        return cargoRepository.findByDescriptionContaining(description, PageRequest.of(page, size)).map(c -> CargoMapper.INSTANCE.entityToDTO(c));
    }


}
