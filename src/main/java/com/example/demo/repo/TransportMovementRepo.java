package com.example.demo.repo;

import com.example.demo.models.productsdelivery.ProductDelivery;
import com.example.demo.models.productsdelivery.TransportMovement;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransportMovementRepo extends JpaRepository<TransportMovement, Long> {

    @EntityGraph(value = "graph.TransportMovementDeliverySpecification", type = EntityGraph.EntityGraphType.LOAD)
    Optional<TransportMovement> findById(Long id);

    @EntityGraph(value = "graph.TransportMovement", type = EntityGraph.EntityGraphType.LOAD)
    List<TransportMovement> findAll();

}
