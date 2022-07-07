package com.example.demo.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TransportMovementRepoTest {

    @Autowired
    TransportMovementRepo transportMovementRepo;

}