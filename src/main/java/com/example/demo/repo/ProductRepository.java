package com.example.demo.repo;

import com.example.demo.models.products.HygieneProduct;
import com.example.demo.models.products.Product;
import com.example.demo.models.products.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("from HygieneProduct")
    public List<Product> findAllHygieneProducts();

    @Query("from FoodProduct")
    public List<Product> findAllFoodProducts();

    @Query("from MedicalProduct")
    public List<Product> findAllMedicalProducts();
}
