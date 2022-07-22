package com.example.demo.repo;

import com.example.demo.models.cargo.CargoActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoActivityRepository extends JpaRepository<CargoActivity, Long> {
}
