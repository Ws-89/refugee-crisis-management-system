package com.example.demo.repo;

import com.example.demo.models.refugees.Refugee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefugeeRepo extends JpaRepository<Refugee, Long> {
}
