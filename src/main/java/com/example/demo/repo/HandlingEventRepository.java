package com.example.demo.repo;

import com.example.demo.models.productsdelivery.HandlingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HandlingEventRepository extends JpaRepository<HandlingEvent, Long> {

    @Query("select s from HandlingEvent s where s.transportMovement.transportMovementId = ?1")
    public List<HandlingEvent> findAllByTransportMovementId(Long id);

}
