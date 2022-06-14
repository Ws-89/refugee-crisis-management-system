package com.example.demo.repo;

import com.example.demo.models.productsdelivery.HandlingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HandlingEventRepository extends JpaRepository<HandlingEvent, Long> {
}
