package com.example.demo.repo;

import com.example.demo.models.productsdelivery.TransportMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TransportMovementRepo extends JpaRepository<TransportMovement, Long> {


}
