package com.example.demo.repo;

import com.example.demo.models.productsdelivery.DeliveryMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryMovementRepository extends JpaRepository<DeliveryMovement, Long> {
}
