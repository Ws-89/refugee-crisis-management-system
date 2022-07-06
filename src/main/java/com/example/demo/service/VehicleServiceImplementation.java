package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.models.vehicles.Vehicle;
import com.example.demo.models.vehicles.VehicleDTO;
import com.example.demo.repo.VehicleRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImplementation implements VehicleService {

    private final VehicleRepository vehicleRepository;
//    @PersistenceContext
    private final EntityManager em;

    public VehicleServiceImplementation(VehicleRepository vehicleRepository, EntityManager entityManager) {
        this.vehicleRepository = vehicleRepository;
        this.em = entityManager;
    }


    @Override
    public Vehicle findById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Vehicle with id %s not found", id)));
    }


    @Override
    public Vehicle saveVehicle(Vehicle source) {
        Optional<Vehicle> ifExists = vehicleRepository.findByLicensePlate(source.getLicensePlate());
        if(ifExists.isPresent()) {
            throw new IllegalStateException(String.format("Vehicle with license plate %s", source.getLicensePlate()));
        }
        Vehicle vehicle = new Vehicle();
        vehicle.update(source);

        return vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional
    public Vehicle updateVehicle(Vehicle source) {
        Vehicle vehicleToUpdate = findById(source.getVehicleId());
        vehicleToUpdate.update(source);
        return vehicleRepository.save(vehicleToUpdate);
    }

    @Override
    public Long deleteVehicle(Long id) {
        Vehicle vehicleToDelete = findById(id);
        vehicleRepository.delete(vehicleToDelete);
        return vehicleToDelete.getVehicleId();
    }

    @Override
    @Transactional
    public List<Vehicle> findAllVehicles() {

//        EntityGraph<?> graph = em.createEntityGraph("graph.VehicleTransportMovement");
//        TypedQuery<Vehicle> q = em.createQuery("SELECT v FROM Vehicle v", Vehicle.class);
//        q.setHint("javax.persistence.fetchgraph",graph);
//        return q.getResultList();
        return vehicleRepository.findAll();
    }

    public List<VehicleDTO> findAllVehiclesWithoutTransportMovement(){
        TypedQuery<VehicleDTO> q = em.createQuery("SELECT new com.example.demo.models.vehicles.VehicleDTO(v.vehicleId,v.brand,v.model,v.engine," +
                "v.capacity, v.vehicleCategory, v.licensePlate) FROM Vehicle v", VehicleDTO.class);
        return q.getResultList();
    }

    @Override
    public List<Double> highestCapacity(){
        return vehicleRepository.findAll().stream().map(v -> v.getCapacity()).sorted(Double::compareTo).distinct().collect(Collectors.toList());
    }
}
