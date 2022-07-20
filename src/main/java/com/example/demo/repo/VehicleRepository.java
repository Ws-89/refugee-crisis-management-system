package com.example.demo.repo;

import com.example.demo.models.productsdelivery.ProductDelivery;
import com.example.demo.models.vehicles.Vehicle;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @EntityGraph(value = "graph.VehicleTransportMovement", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Vehicle> findById(Long id);

    public Optional<Vehicle> findByLicensePlate(String licensePlate);
}
