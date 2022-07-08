package com.example.demo.service;

import com.example.demo.dto.DeliveryAddressDTO;
import com.example.demo.dto.DeliveryHistoryDTO;
import com.example.demo.dto.HandlingEventDTO;
import com.example.demo.mappers.HandlingEventMapper;
import com.example.demo.models.productsdelivery.*;
import com.example.demo.models.vehicles.Vehicle;
import com.example.demo.repo.HandlingEventRepository;
import com.example.demo.repo.ProductDeliveryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HandlingEventServiceImplementationTest {

    @Mock
    private HandlingEventRepository handlingEventRepository;

    @Mock
    private ProductDeliveryRepository productDeliveryRepository;

    @Mock
    private EntityManager entityManagerMock;

    @InjectMocks
    private HandlingEventServiceImplementation handlingEventService;

    @Test
    void shouldFindAllHandlingEventsOfTransportMovement() {
        DeliveryAddress startingAddress = DeliveryAddress.builder().city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliveryAddress deliveryAddress = DeliveryAddress.builder().city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliverySpecification deliverySpecification = DeliverySpecification.builder().deliveryAddress(deliveryAddress).build();
        Vehicle vehicle = Vehicle.builder().vehicleCategory("Van").capacity(900.0).engine("2.0").brand("Renault").build();

        ProductDelivery productDelivery = ProductDelivery.builder().deliverySpecification(deliverySpecification).startingAddress(startingAddress).build();
        ProductDelivery productDelivery2 = ProductDelivery.builder().deliverySpecification(deliverySpecification).startingAddress(startingAddress).build();
        ProductDelivery productDelivery3 = ProductDelivery.builder().deliverySpecification(deliverySpecification).startingAddress(startingAddress).build();
        ProductDelivery productDelivery4 = ProductDelivery.builder().deliverySpecification(deliverySpecification).startingAddress(startingAddress).build();

        DeliveryHistory deliveryHistory = DeliveryHistory.builder().productDelivery(productDelivery).build();
        DeliveryHistory deliveryHistory2 = DeliveryHistory.builder().productDelivery(productDelivery2).build();
        DeliveryHistory deliveryHistory3 = DeliveryHistory.builder().productDelivery(productDelivery3).build();
        DeliveryHistory deliveryHistory4 = DeliveryHistory.builder().productDelivery(productDelivery4).build();

        HandlingEvent handlingEvent = HandlingEvent.builder().deliveryHistory(deliveryHistory).state(HandlingEventState.INITIALIZING_EVENT).build();
        HandlingEvent handlingEvent2 = HandlingEvent.builder().deliveryHistory(deliveryHistory2).state(HandlingEventState.INITIALIZING_EVENT).build();
        HandlingEvent handlingEvent3 = HandlingEvent.builder().deliveryHistory(deliveryHistory3).state(HandlingEventState.INITIALIZING_EVENT).build();
        HandlingEvent handlingEvent4 = HandlingEvent.builder().deliveryHistory(deliveryHistory4).state(HandlingEventState.INITIALIZING_EVENT).build();

        List<HandlingEvent> emptyList = new ArrayList<>();

        List<HandlingEvent> events = Arrays.asList(handlingEvent, handlingEvent2, handlingEvent3, handlingEvent4);

        TransportMovement transportMovement = TransportMovement.builder().transportMovementId(1L)
                .startingAddress(startingAddress).deliverySpecification(deliverySpecification).handlingEvents(emptyList)
                .vehicle(vehicle).build();

        transportMovement.addHandlingEvent(handlingEvent);
        transportMovement.addHandlingEvent(handlingEvent2);
        transportMovement.addHandlingEvent(handlingEvent3);

        TypedQuery queryMock = Mockito.mock(TypedQuery.class);
        when(queryMock.getResultList()).thenReturn(events);

        when(entityManagerMock.createQuery(HandlingEventServiceImplementation.GET_ALL_HANDLING_EVENTS_BY_TRANSPORT_MOVEMENT_ID, HandlingEvent.class)).thenReturn(queryMock);

        List<HandlingEventDTO> handlingEventDTOS = handlingEventService.findAllByTransportMovementId(1L);
        assertThat(handlingEventDTOS.size()).isEqualTo(4);
    }

    @Test
    void shouldSaveHandlingEvent() {

        //given
        DeliveryAddress startingAddress = DeliveryAddress.builder().city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliveryAddress deliveryAddress = DeliveryAddress.builder().city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliverySpecification deliverySpecification = DeliverySpecification.builder().deliveryAddress(deliveryAddress).build();
        ProductDelivery productDelivery = ProductDelivery.builder().deliverySpecification(deliverySpecification).startingAddress(startingAddress).build();
        List<HandlingEvent> emptyList = new ArrayList<>();
        DeliveryHistory deliveryHistory = DeliveryHistory.builder().productDelivery(productDelivery).handlingEvents(emptyList).build();
        productDelivery.setDeliveryHistory(deliveryHistory);

        Vehicle vehicle = Vehicle.builder().vehicleCategory("Van").capacity(900.0).engine("2.0").brand("Renault").build();

        List<HandlingEvent> emptyList2 = new ArrayList<>();
        TransportMovement transportMovement = TransportMovement.builder().transportMovementId(1L)
                .startingAddress(startingAddress).deliverySpecification(deliverySpecification).handlingEvents(emptyList2)
                .vehicle(vehicle).build();

        HandlingEvent handlingEvent = HandlingEvent.builder().state(HandlingEventState.INITIALIZING_EVENT).build();

        EntityGraph<?> graph = null;
        Map<String, Object> hints = new HashMap<String, Object>();
        hints.put("javax.persistence.fetchgraph", graph);

        when(productDeliveryRepository.findById(1L)).thenReturn(Optional.of(productDelivery));
        when(entityManagerMock.find(TransportMovement.class, 1L, hints)).thenReturn(transportMovement);

        // when
        HandlingEventDTO handlingEventDTO = handlingEventService.saveHandlingEvent(handlingEvent, 1L, 1L);

        ArgumentCaptor<HandlingEvent> eventArgumentCaptor = ArgumentCaptor.forClass(HandlingEvent.class);
        verify(handlingEventRepository).save(eventArgumentCaptor.capture());
        HandlingEvent eventArgumentCaptorValue = eventArgumentCaptor.getValue();

        // then
        assertThat(eventArgumentCaptorValue.getTransportMovement()).isEqualTo(transportMovement);
        assertThat(eventArgumentCaptorValue.getDeliveryHistory().getProductDelivery()).isEqualTo(productDelivery);
    }

    @Test
    void isAlreadyInTransportShouldBeTrue(){

        //given
        DeliveryAddress startingAddress = DeliveryAddress.builder().city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliveryAddress deliveryAddress = DeliveryAddress.builder().city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliverySpecification deliverySpecification = DeliverySpecification.builder().deliveryAddress(deliveryAddress).build();
        ProductDelivery productDelivery = ProductDelivery.builder().deliverySpecification(deliverySpecification).startingAddress(startingAddress).build();
        List<HandlingEvent> emptyList = new ArrayList<>();
        DeliveryHistory deliveryHistory = DeliveryHistory.builder().productDelivery(productDelivery).handlingEvents(emptyList).build();
        productDelivery.setDeliveryHistory(deliveryHistory);

        Vehicle vehicle = Vehicle.builder().vehicleCategory("Van").capacity(900.0).engine("2.0").brand("Renault").build();
        List<HandlingEvent> emptyList2 = new ArrayList<>();
        TransportMovement transportMovement = TransportMovement.builder().transportMovementId(1L)
                .startingAddress(startingAddress).deliverySpecification(deliverySpecification).handlingEvents(emptyList2)
                .vehicle(vehicle).build();

        HandlingEvent handlingEvent = HandlingEvent.builder().state(HandlingEventState.INITIALIZING_EVENT).build();

        // when
        EntityGraph<?> graph = null;
        Map<String, Object> hints = new HashMap<String, Object>();
        hints.put("javax.persistence.fetchgraph", graph);

        productDelivery.getDeliveryHistory().addEvent(handlingEvent);
        transportMovement.addHandlingEvent(handlingEvent);

        when(productDeliveryRepository.findById(1L)).thenReturn(Optional.of(productDelivery));
        assertThrows(IllegalStateException.class, () -> handlingEventService.saveHandlingEvent(handlingEvent, 1L, 1L));
    }

    @Test
    void updateHandlingEvent() {
//        HandlingEvent handlingEvent = HandlingEvent.builder().state(HandlingEventState.INITIALIZING_EVENT).build();
//        HandlingEvent updateHandlingEvent = HandlingEvent.builder().state(HandlingEventState.LOADING_EVENT).build();
//
//        when(handlingEventRepository.findById(1L)).thenReturn(Optional.of(handlingEvent));
//        ArgumentCaptor<HandlingEvent> eventArgumentCaptor = ArgumentCaptor.forClass(HandlingEvent.class);
//        verify(handlingEventRepository).save(eventArgumentCaptor.capture());
//
//        HandlingEvent eventArgumentCaptorValue = eventArgumentCaptor.getValue();
//        HandlingEventDTO handlingEventDTO = handlingEventService.updateHandlingEvent(updateHandlingEvent);
//
//        assertThat(eventArgumentCaptorValue.getState()).isEqualTo(updateHandlingEvent.getState());
    }

    @Test
    void removeHandlingEvent() {
    }
}