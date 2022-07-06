package com.example.demo.repo;

import com.example.demo.models.productsdelivery.TransportMovement;
import com.example.demo.models.productsdelivery.TransportMovementDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TransportMovementRepo extends JpaRepository<TransportMovement, Long> {

    @Transactional
    @Query("select s from TransportMovement s where s.transportMovementId = ?1")
    TransportMovementDTO findDtoByID(Long id);


}
