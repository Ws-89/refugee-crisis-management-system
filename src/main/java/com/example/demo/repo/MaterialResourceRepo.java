package com.example.demo.repo;

import com.example.demo.models.materialResources.MaterialResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MaterialResourceRepo extends JpaRepository<MaterialResource, Long> {
}
