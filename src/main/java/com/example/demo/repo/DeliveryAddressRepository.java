package com.example.demo.repo;

import com.example.demo.models.cargo.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {

    Optional<DeliveryAddress> findByCity(String city);
    Optional<DeliveryAddress> findByPostCode(String postCode);
    Optional<DeliveryAddress> findByStreet(String street);
}
