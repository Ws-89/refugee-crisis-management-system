package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.models.vehicles.Vehicle;
import com.example.demo.models.vehicles.VehicleDTO;
import com.example.demo.models.vehicles.VehicleMapper;
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

    private final EntityManager em;

    public VehicleServiceImplementation(VehicleRepository vehicleRepository, EntityManager entityManager) {
        this.vehicleRepository = vehicleRepository;
        this.em = entityManager;
    }

 /* returns vehicle with transport movement list */
    @Override
    public VehicleDTO findById(Long id) {
        return vehicleRepository.findById(id)
                .map(v -> VehicleMapper.INSTANCE.entityToDTO(v))
                .orElseThrow(() -> new NotFoundException(String.format("Vehicle with id %s not found", id)));
    }


    @Override
    public VehicleDTO saveVehicle(Vehicle source) {
        Optional<Vehicle> ifExists = vehicleRepository.findByLicensePlate(source.getLicensePlate());
        if(ifExists.isPresent()) {
            throw new IllegalStateException(String.format("Vehicle with license plate %s", source.getLicensePlate()));
        }
        Vehicle vehicle = new Vehicle();
        vehicle.update(source);

        return VehicleMapper.INSTANCE.entityToDTO(vehicleRepository.save(vehicle));
    }

    @Override
    @Transactional
    public VehicleDTO updateVehicle(Vehicle source) {
        return vehicleRepository.findById(source.getVehicleId())
                .map(v -> {
                    v.update(source);
                    return VehicleMapper.INSTANCE.entityToDTO(vehicleRepository.save(v)); })
                .orElseThrow(() -> new NotFoundException(String.format("Vehicle with id %s not found", source.getVehicleId())));
    }

    @Override
    public Long deleteVehicle(Long id) {
        return vehicleRepository.findById(id)
                .map(v -> {
                    vehicleRepository.delete(v);
                    return v.getVehicleId();
                })
                .orElseThrow(() -> new NotFoundException(String.format("Vehicle with id %s not found", id)));
    }

    @Override
    @Transactional
    public List<VehicleDTO> findAllVehicles() {
        return vehicleRepository.findAll().stream().map(v -> VehicleMapper.INSTANCE.entityToDTO(v)).collect(Collectors.toList());
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
