package com.example.demo.repo;

import com.example.demo.models.productsdelivery.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {

    DeliveryAddress findByCity(String city);
    DeliveryAddress findByPostCode(String postCode);
}
