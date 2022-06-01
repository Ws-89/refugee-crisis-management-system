package com.example.demo.repo;

import com.example.demo.models.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MaterialResourceRepo extends JpaRepository<Product, Long> {
}
