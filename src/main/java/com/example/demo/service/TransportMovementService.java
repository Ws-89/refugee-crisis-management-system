package com.example.demo.service;

import com.example.demo.dto.TransportMovementDTO;
import com.example.demo.dto.TransportMovementFullGraphDTO;
import com.example.demo.mappers.TransportMovementFullGraphMapper;
import com.example.demo.mappers.TransportMovementMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.products.Status;
import com.example.demo.models.productsdelivery.*;
import com.example.demo.models.vehicles.Vehicle;
import com.example.demo.repo.DeliveryAddressRepository;
import com.example.demo.repo.ProductDeliveryRepository;
import com.example.demo.repo.TransportMovementRepo;
import com.example.demo.repo.VehicleRepository;
import com.example.demo.requests.AssignPackageToTransportRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransportMovementService  {

    private final TransportMovementRepo transportMovementRepo;
    private final DeliveryAddressRepository deliveryAddressRepository;
    private final VehicleRepository vehicleRepository;
    private final ProductDeliveryRepository productDeliveryRepository;
    private final EntityManager em;

    public static final String GET_ALL_TRANSPORT_MOVEMENTS = "from TransportMovement";

    public TransportMovementFullGraphDTO findById(Long id) {
        TransportMovement transportMovement = transportMovementRepo.findById(id)
                .orElseThrow(()-> new NotFoundException("Transport movement not found"));

        EntityGraph<?> graph = em.createEntityGraph("graph.TransportMovementWithPackages");
        TransportMovement wayBills = em.find(TransportMovement.class, id, Collections.singletonMap(
                "javax.persistence.loadgraph",
                graph
        ));

        transportMovement.setWayBills(wayBills.getWayBills());
        return TransportMovementFullGraphMapper.INSTANCE.entityToDTO(transportMovement);
    }


    @Transactional
    public TransportMovementDTO save(TransportMovement transportMovement) {

        DeliveryAddress startingAddress = deliveryAddressRepository.findById(
                transportMovement.getStartingAddress().getDeliveryAddressId()
        ).orElseThrow(()-> new NotFoundException("Starting address not found"));

        DeliveryAddress deliveryAddress = deliveryAddressRepository.findById(
                transportMovement.getStartingAddress().getDeliveryAddressId()
        ).orElseThrow(()-> new NotFoundException("Starting address not found"));


        List<TransportMovementSpecification> transportMovementSpecifications = transportMovement.getTransportMovementSpecifications().stream()
                .map(s -> {
                    TransportMovementSpecification transportMovementSpecification = s;
                    DeliveryAddress address = deliveryAddressRepository.findById(s.getDeliveryAddress().getDeliveryAddressId())
                            .orElseThrow(()-> new NotFoundException("Starting address not found"));
                    transportMovementSpecification.setDeliveryAddress(address);
                    return transportMovementSpecification;
                }).collect(Collectors.toList());

        Vehicle vehicle = vehicleRepository.findById(transportMovement.getVehicle().getVehicleId()).orElseThrow(() -> new NotFoundException("Vehicle not found"));

        TransportMovement tmToSave = TransportMovement.builder()
                .startingAddress(startingAddress)
                .deliveryAddress(deliveryAddress)
                .transportMovementSpecifications(transportMovementSpecifications)
                .weightOfTheGoods(0.0)
                .vehicle(vehicle)
                .wayBills(transportMovement.getWayBills())
                .build();

        vehicle.addTransportMovement(tmToSave);
        transportMovementSpecifications.stream().forEach(tms -> tms.setTransportMovement(tmToSave));

        return TransportMovementMapper.INSTANCE.entityToDTO(transportMovementRepo.save(tmToSave));
    }



    public Long delete(Long id) {
        return transportMovementRepo.findById(id).map(
                transport -> {transportMovementRepo.delete(transport);
                return transport.getTransportMovementId();
                }
        ).orElseThrow(()-> new NotFoundException("Transport movement not found"));
    }


    public TransportMovementDTO update(TransportMovement transportMovement) {
        TransportMovement result = transportMovementRepo.findById(transportMovement.getTransportMovementId())
                .map(transport -> {
                    TransportMovement updatedTransportMovement = transport;
                    updatedTransportMovement.setTransportMovementSpecifications(transportMovement.getTransportMovementSpecifications());
                    updatedTransportMovement.setWayBills(transportMovement.getWayBills());
                    updatedTransportMovement.setStartingAddress(transportMovement.getStartingAddress());
                    updatedTransportMovement.setVehicle(transportMovement.getVehicle());
                    return updatedTransportMovement;
                })
                .orElseThrow(()-> new NotFoundException("Transport movement not found"));
        return TransportMovementMapper.INSTANCE.entityToDTO(transportMovementRepo.save(result));
    }


    @Transactional
    public List<TransportMovementDTO> findAll() {
        return transportMovementRepo.findAll().stream().map(t -> TransportMovementMapper.INSTANCE.entityToDTO(t)).collect(Collectors.toList());
    }

    @Transactional
    public TransportMovementDTO addAShipment(AssignPackageToTransportRequest assignPackageToTransportRequest){
        ProductDelivery productDelivery = productDeliveryRepository.findById(assignPackageToTransportRequest.getDeliveryId())
                .orElseThrow(() -> new NotFoundException(String.format("Package with id %s not found", assignPackageToTransportRequest.getDeliveryId())));

        TransportMovement transportMovement = transportMovementRepo.findById(assignPackageToTransportRequest.getTransportId())
                .orElseThrow(() -> new NotFoundException(String.format("Transport with id %s not found", assignPackageToTransportRequest.getTransportId())));



        Boolean result = transportMovement.addProductDelivery(productDelivery.getDeliveryHistory());

        if(assignPackageToTransportRequest.getFinalDestination() && result){
            productDelivery.setStatus(Status.Reserved);
        }
        return TransportMovementMapper.INSTANCE.entityToDTO(transportMovementRepo.save(transportMovement));
    }

}
