package com.example.demo.repo;

import com.example.demo.models.productsdelivery.ProductDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDeliveryRepository extends JpaRepository<ProductDelivery, Long> {

}
