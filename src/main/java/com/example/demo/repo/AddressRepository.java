package com.example.demo.repo;

import com.example.demo.models.cargo.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {


    Page<Address> findByCityContaining(String city, Pageable page);
    Optional<Address> findByPostCode(String postCode);
    Optional<Address> findByStreet(String street);
}
