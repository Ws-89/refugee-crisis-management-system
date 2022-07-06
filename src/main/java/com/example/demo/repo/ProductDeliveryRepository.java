package com.example.demo.repo;

import com.example.demo.models.productsdelivery.HandlingEvent;
import com.example.demo.models.productsdelivery.ProductDelivery;
import com.example.demo.models.productsdelivery.ProductDeliveryDTO;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDeliveryRepository extends JpaRepository<ProductDelivery, Long> {

    @Override
    @EntityGraph(value = "graph.WholeProductDelivery", type= EntityGraph.EntityGraphType.LOAD)
    List<ProductDelivery> findAll();

    @EntityGraph(value = "graph.DeliveryOnlyWithProducts", type = EntityGraph.EntityGraphType.LOAD)
    Optional<ProductDelivery> findById(Long id);
}
