package com.example.demo.repo;

import com.example.demo.models.cargo.Cargo;
import com.example.demo.models.products.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CargoRepository extends PagingAndSortingRepository<Cargo, Long> {


    @Override
    @EntityGraph(value = "graph.WholeCargo", type= EntityGraph.EntityGraphType.LOAD)
    List<Cargo> findAll();

    @EntityGraph(value = "graph.WholeCargo", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Cargo> findById(Long id);

    @EntityGraph(value = "graph.WholeCargo", type = EntityGraph.EntityGraphType.LOAD)
    Page<Cargo> findByStatusAndDescriptionContaining(Status status, String description, Pageable page);
}
