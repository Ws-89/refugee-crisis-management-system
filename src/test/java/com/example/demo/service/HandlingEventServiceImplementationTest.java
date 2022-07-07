package com.example.demo.service;

import com.example.demo.dto.HandlingEventDTO;
import com.example.demo.models.productsdelivery.HandlingEvent;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class HandlingEventServiceImplementationTest {

    private final HandlingEventService handlingEventService;

    HandlingEventServiceImplementationTest(HandlingEventService handlingEventService) {
        this.handlingEventService = handlingEventService;
    }

    @Test
    void handlingEventsOfTransportMovement() {
    }

    @Test
    void handlingEventsOfProductDelivery() {
    }

    @Test
    void saveHandlingEvent() {

        HandlingEventDTO handlingEvent = this.handlingEventService.saveHandlingEvent(null, 26L, 44L);
        assertThat(handlingEvent).isInstanceOf(HandlingEvent.class);
    }

    @Test
    void updateHandlingEvent() {
    }

    @Test
    void removeHandlingEvent() {
    }
}