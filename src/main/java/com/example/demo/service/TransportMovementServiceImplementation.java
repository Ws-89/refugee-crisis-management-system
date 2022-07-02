package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.models.productsdelivery.DeliveryAddress;
import com.example.demo.models.productsdelivery.DeliverySpecification;
import com.example.demo.models.productsdelivery.TransportMovement;
import com.example.demo.models.vehicles.Vehicle;
import com.example.demo.repo.DeliveryAddressRepository;
import com.example.demo.repo.TransportMovementRepo;
import com.example.demo.repo.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TransportMovementServiceImplementation implements TransportMovementService {

    private final TransportMovementRepo transportMovementRepo;
    private final DeliveryAddressRepository deliveryAddressRepository;
    private final VehicleRepository vehicleRepository;

    public TransportMovementServiceImplementation(TransportMovementRepo transportMovementRepo, DeliveryAddressRepository deliveryAddressRepository, VehicleRepository vehicleRepository) {
        this.transportMovementRepo = transportMovementRepo;
        this.deliveryAddressRepository = deliveryAddressRepository;
        this.vehicleRepository = vehicleRepository;
    }


    @Override
    public TransportMovement findById(Long id) {
        return transportMovementRepo.findById(id).orElseThrow(()-> new NotFoundException("Transport movement not found"));
    }

    @Override
    public TransportMovement save(TransportMovement transportMovement) {
        DeliveryAddress startingAddress = deliveryAddressRepository.findById(
                transportMovement.getStartingAddress().getDeliveryAddressId()
        ).orElseThrow(()-> new NotFoundException("Starting address not found"));
        DeliveryAddress destinationAddress = deliveryAddressRepository.findById(
                transportMovement.getDeliverySpecification().getDeliveryAddress().getDeliveryAddressId()
                )
                    .orElseThrow(()-> new NotFoundException("Destination address not found"));
        Vehicle vehicle = vehicleRepository.findById(
                transportMovement.getVehicle().getVehicleId()
        ).orElseThrow(()-> new NotFoundException("Vehicle not found"));

        DeliverySpecification deliverySpecification = transportMovement.getDeliverySpecification();
        deliverySpecification.setDeliveryAddress(destinationAddress);

//        DeliverySpecification deliverySpecification = new DeliverySpecification();
//        deliverySpecification.setDeliveryAddress(destinationAddress);
//        destinationAddress.addDeliverySpecification(deliverySpecification);

        TransportMovement tmToSave = TransportMovement.builder()
                .startingAddress(startingAddress)
                .deliverySpecification(deliverySpecification)
                .vehicle(vehicle)
                .handlingEvents(transportMovement.getHandlingEvents())
                .build();

        vehicle.addTransportMovement(tmToSave);

        return transportMovementRepo.save(tmToSave);
    }

    @Override
    public Long delete(Long id) {
        return transportMovementRepo.findById(id).map(
                transport -> {transportMovementRepo.delete(transport);
                return transport.getTransportMovementId();
                }
        ).orElseThrow(()-> new NotFoundException("Transport movement not found"));
    }

    @Override
    public TransportMovement update(TransportMovement transportMovement) {
        return transportMovementRepo.findById(transportMovement.getTransportMovementId())
                .map(transport -> {
                    transport.setDeliverySpecification(transportMovement.getDeliverySpecification());
                    transport.setHandlingEvents(transportMovement.getHandlingEvents());
                    transport.setStartingAddress(transportMovement.getStartingAddress());
                    transport.setVehicle(transportMovement.getVehicle());
                    return transportMovementRepo.save(transport);
                })
                .orElseThrow(()-> new NotFoundException("Transport movement not found"));
    }

    @Override
    public Set<TransportMovement> findAll() {
        return transportMovementRepo.findAll().stream().collect(Collectors.toSet());
    }
}
