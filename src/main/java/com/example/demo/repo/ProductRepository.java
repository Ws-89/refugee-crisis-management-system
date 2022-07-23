package com.example.demo.repo;

import com.example.demo.models.products.Product;
import com.example.demo.models.products.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Page<Product> findByNameContaining(String name, Pageable page);

    Page<Product> findByReservedAndNameContaining(Status status, String name, Pageable page);

//    @Query("from HygieneProduct")
//    public List<Product> findAllHygieneProducts();
//
//    @Query("from FoodProduct")
//    public List<Product> findAllFoodProducts();
//
//    @Query("from MedicalProduct")
//    public List<Product> findAllMedicalProducts();
}
