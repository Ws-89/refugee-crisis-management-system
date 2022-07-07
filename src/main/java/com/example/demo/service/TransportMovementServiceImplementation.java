package com.example.demo.service;

import com.example.demo.dto.TransportMovementDTO;
import com.example.demo.dto.TransportMovementMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.productsdelivery.DeliveryAddress;
import com.example.demo.models.productsdelivery.DeliverySpecification;
import com.example.demo.models.productsdelivery.TransportMovement;
import com.example.demo.models.vehicles.Vehicle;
import com.example.demo.repo.DeliveryAddressRepository;
import com.example.demo.repo.TransportMovementRepo;
import com.example.demo.repo.VehicleRepository;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Service;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransportMovementServiceImplementation implements TransportMovementService  {

    private final TransportMovementRepo transportMovementRepo;
    private final DeliveryAddressRepository deliveryAddressRepository;
    private final VehicleRepository vehicleRepository;
    private final EntityManager em;

    public TransportMovementServiceImplementation(TransportMovementRepo transportMovementRepo, DeliveryAddressRepository deliveryAddressRepository, VehicleRepository vehicleRepository, EntityManager entityManager) {
        this.transportMovementRepo = transportMovementRepo;
        this.deliveryAddressRepository = deliveryAddressRepository;
        this.vehicleRepository = vehicleRepository;
        this.em = entityManager;
    }


    @Override
    public TransportMovementDTO findById(Long id) {
        return transportMovementRepo.findById(id).map(t -> TransportMovementMapper.INSTANCE.entityToDTO(t))
                .orElseThrow(()-> new NotFoundException("Transport movement not found"));
    }

    @Override
    @Transactional
    public TransportMovementDTO save(TransportMovement transportMovement) {

        DeliveryAddress startingAddress = deliveryAddressRepository.findById(
                transportMovement.getStartingAddress().getDeliveryAddressId()
        ).orElseThrow(()-> new NotFoundException("Starting address not found"));

        DeliveryAddress destinationAddress = deliveryAddressRepository.findById(
                transportMovement.getDeliverySpecification().getDeliveryAddress().getDeliveryAddressId()
                )
                    .orElseThrow(()-> new NotFoundException("Destination address not found"));

        EntityGraph<?> graph = em.getEntityGraph("graph.VehicleTransportMovement");

        Map<String, Object> hints = new HashMap<String, Object>();
        hints.put("javax.persistence.fetchgraph",graph);

        Vehicle vehicle = em.find(Vehicle.class,transportMovement.getVehicle().getVehicleId(), hints);

        DeliverySpecification deliverySpecification = transportMovement.getDeliverySpecification();
        deliverySpecification.setDeliveryAddress(destinationAddress);

        TransportMovement tmToSave = TransportMovement.builder()
                .startingAddress(startingAddress)
                .deliverySpecification(deliverySpecification)
                .vehicle(vehicle)
                .handlingEvents(transportMovement.getHandlingEvents())
                .build();

        vehicle.addTransportMovement(tmToSave);

        return TransportMovementMapper.INSTANCE.entityToDTO(transportMovementRepo.save(tmToSave));
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
    public TransportMovementDTO update(TransportMovement transportMovement) {
        return transportMovementRepo.findById(transportMovement.getTransportMovementId())
                .map(transport -> {
                    transport.setDeliverySpecification(transportMovement.getDeliverySpecification());
                    transport.setHandlingEvents(transportMovement.getHandlingEvents());
                    transport.setStartingAddress(transportMovement.getStartingAddress());
                    transport.setVehicle(transportMovement.getVehicle());
                    return TransportMovementMapper.INSTANCE.entityToDTO(transportMovementRepo.save(transport));
                })
                .orElseThrow(()-> new NotFoundException("Transport movement not found"));
    }

    @Override
    @Transactional
    public List<TransportMovementDTO> findAll() {
        EntityGraph<?> graph = em.getEntityGraph("graph.TransportMovementHandlingEvents");

        TypedQuery<TransportMovement> query = em.createQuery("from TransportMovement", TransportMovement.class);
        query.setHint("javax.persistence.fetchgraph",graph);

        return query.getResultList().stream().map(t -> TransportMovementMapper.INSTANCE.entityToDTO(t)).collect(Collectors.toList());
    }

}
