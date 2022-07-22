package com.example.demo.repo;

import com.example.demo.models.cargo.DeliveryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryHistoryRepository extends JpaRepository<DeliveryHistory, Long> {
}
