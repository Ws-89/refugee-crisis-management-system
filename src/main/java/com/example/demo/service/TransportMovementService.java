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
@Transactional
public class TransportMovementService  {

    private final TransportMovementRepo transportMovementRepo;
    private final DeliveryAddressRepository deliveryAddressRepository;
    private final VehicleRepository vehicleRepository;
    private final ProductDeliveryRepository productDeliveryRepository;
    private final EntityManager em;

    public static final String GET_ALL_TRANSPORT_MOVEMENTS = "from TransportMovement";

    @Transactional
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

        Vehicle vehicle = vehicleRepository.findById(transportMovement.getVehicle().getVehicleId()).orElseThrow(() -> new NotFoundException("Vehicle not found"));

        TransportMovement tmToSave = TransportMovement.builder()
                .startingAddress(startingAddress)
                .deliveryAddress(deliveryAddress)
                .weightOfTheGoods(0.0)
                .vehicle(vehicle)
                .wayBills(transportMovement.getWayBills())
                .build();

        vehicle.addTransportMovement(tmToSave);

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
            productDelivery.getDeliveryHistory().setFinalDestinationId(assignPackageToTransportRequest.getTransportId());
        }

        return TransportMovementMapper.INSTANCE.entityToDTO(transportMovementRepo.save(transportMovement));
    }

    @Transactional
    public TransportMovementFullGraphDTO removeAShipment(Long transportId, Long wayBillId){
        TransportMovement transportMovement = transportMovementRepo.findById(transportId)
                .orElseThrow(() -> new NotFoundException(String.format("Transport with id %s not found", transportId)));

        DeliveryHistory packageToDelete = transportMovement.getWayBills().stream().filter(x -> x.getDeliveryHistoryId() == wayBillId)
                .findFirst().orElseThrow(() -> new NotFoundException(String.format("Package does not exist in this transport")));

        if(transportMovement.getTransportMovementId() == packageToDelete.getFinalDestinationId()){
            packageToDelete.getProductDelivery().setStatus(Status.Available);
        }

        if(transportMovement.removePackage(packageToDelete)){
            productDeliveryRepository.save(packageToDelete.getProductDelivery());
        }else {
            throw new IllegalStateException("Cannot delete the package");
        }

        return TransportMovementFullGraphMapper.INSTANCE.entityToDTO(transportMovementRepo.save(transportMovement));
    }

    @Transactional
    public TransportMovementFullGraphDTO changeRouteOrderUp(Long transportId, Long transportMovementSpecificationId){
        TransportMovement transportMovement = transportMovementRepo.findById(transportId)
                .orElseThrow(() -> new NotFoundException(String.format("Transport with id %s not found", transportId)));

        TransportMovementSpecification tms = transportMovement.getTransportMovementSpecifications().stream()
                .filter(x -> x.getTransportMovementSpecificationId() == transportMovementSpecificationId)
                .findFirst().orElseThrow(() -> new NotFoundException("No such address"));

        int index = transportMovement.getTransportMovementSpecifications().indexOf(tms);
        if(index < transportMovement.getTransportMovementSpecifications().size()-1){
            transportMovement.getTransportMovementSpecifications().remove(index);
            transportMovement.getTransportMovementSpecifications().add(index+1, tms);
        }
        return TransportMovementFullGraphMapper.INSTANCE.entityToDTO(transportMovementRepo.save(transportMovement));
    }

    @Transactional
    public TransportMovementFullGraphDTO changeRouteOrderDown(Long transportId, Long transportMovementSpecificationId){
        TransportMovement transportMovement = transportMovementRepo.findById(transportId)
                .orElseThrow(() -> new NotFoundException(String.format("Transport with id %s not found", transportId)));

        TransportMovementSpecification tms = transportMovement.getTransportMovementSpecifications().stream()
                .filter(x -> x.getTransportMovementSpecificationId() == transportMovementSpecificationId)
                .findFirst().orElseThrow(() -> new NotFoundException("No such address"));

        int index = transportMovement.getTransportMovementSpecifications().indexOf(tms);
        if(index > 0){
            transportMovement.getTransportMovementSpecifications().remove(index);
            transportMovement.getTransportMovementSpecifications().add(index-1, tms);
        }
        return TransportMovementFullGraphMapper.INSTANCE.entityToDTO(transportMovementRepo.save(transportMovement));
    }

    @Transactional
    public TransportMovementFullGraphDTO generateARoute(Long transportId){
        TransportMovement transportMovement = transportMovementRepo.findById(transportId)
                .orElseThrow(() -> new NotFoundException(String.format("Transport with id %s not found", transportId)));

        List<TransportMovementSpecification> transportMovementSpecifications = transportMovement.getWayBills().stream()
                .map(w -> {
                    DeliveryAddress address = deliveryAddressRepository.findById(w.getProductDelivery().getDeliverySpecification().getDeliveryAddress().getDeliveryAddressId())
                            .orElseThrow(()-> new NotFoundException("Delivery address not found"));

                    TransportMovementSpecification transportMovementSpecification = TransportMovementSpecification.builder()
                            .arrivalTime(w.getProductDelivery().getDeliverySpecification().getArrivalTime())
                            .deliveryAddress(address)
                            .transportMovement(transportMovement)
                            .build();
                    return transportMovementSpecification;
                }).collect(Collectors.toList());
        transportMovement.getTransportMovementSpecifications().removeAll(transportMovement.getTransportMovementSpecifications());
        transportMovementSpecifications.stream().forEach(tms -> transportMovement.getTransportMovementSpecifications().add(tms));
//        transportMovement.generateARoute();
        return TransportMovementFullGraphMapper.INSTANCE.entityToDTO(transportMovementRepo.save(transportMovement));
    }

}
