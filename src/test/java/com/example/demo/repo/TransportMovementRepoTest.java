package com.example.demo.repo;

import com.example.demo.models.productsdelivery.TransportMovementDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransportMovementRepoTest {

    @Autowired
    TransportMovementRepo transportMovementRepo;

    @Test
    void findDtoByID() {
        TransportMovementDTO transportMovement = transportMovementRepo.findDtoByID(59L);

        assertThat(transportMovement.getHandlingEvents()).isNotNull();

    }
}