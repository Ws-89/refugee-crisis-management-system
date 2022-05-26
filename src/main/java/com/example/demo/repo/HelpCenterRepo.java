package com.example.demo.repo;

import com.example.demo.models.HelpCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelpCenterRepo extends JpaRepository<HelpCenter, Long> {
}
