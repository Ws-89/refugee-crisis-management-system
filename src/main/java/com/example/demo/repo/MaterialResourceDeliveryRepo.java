package com.example.demo.repo;

import com.example.demo.models.materialResourceDelivery.MaterialResourceDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialResourceDeliveryRepo extends JpaRepository<MaterialResourceDelivery, Long> {
}
