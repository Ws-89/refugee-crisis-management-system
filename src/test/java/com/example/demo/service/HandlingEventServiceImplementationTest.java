package com.example.demo.service;

import com.example.demo.dto.HandlingEventDTO;
import com.example.demo.models.productsdelivery.*;
import com.example.demo.models.vehicles.Vehicle;
import com.example.demo.repo.HandlingEventRepository;
import com.example.demo.repo.ProductDeliveryRepository;
import com.example.demo.repo.TransportMovementRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.text.html.Option;

import java.time.LocalDateTime;
import java.util.*;

import static com.example.demo.models.products.Status.Available;
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
    private TransportMovementRepo transportMovementRepo;

    @Mock
    private EntityManager entityManagerMock;

    @InjectMocks
    private HandlingEventServiceImplementation handlingEventService;

    @Test
    void shouldReturnHandlingEvent(){
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

        when(handlingEventRepository.findById(1L)).thenReturn(Optional.of(handlingEvent));
        HandlingEventDTO eventDTO = handlingEventService.getHandlingEvent(1L);

        assertThat(eventDTO.getState()).isEqualTo(handlingEvent.getState());
    }

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

        when(productDeliveryRepository.findById(1L)).thenReturn(Optional.of(productDelivery));
        when(transportMovementRepo.findById(1L)).thenReturn(Optional.of(transportMovement));

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
        productDelivery.getDeliveryHistory().addEvent(handlingEvent);
        transportMovement.addHandlingEvent(handlingEvent);

        when(productDeliveryRepository.findById(1L)).thenReturn(Optional.of(productDelivery));
        assertThrows(IllegalStateException.class, () -> handlingEventService.saveHandlingEvent(handlingEvent, 1L, 1L));
    }

    @Test
    void updateHandlingEvent() {
        DeliveryAddress startingAddress = DeliveryAddress.builder().city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliveryAddress deliveryAddress = DeliveryAddress.builder().city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliverySpecification deliverySpecification = DeliverySpecification.builder().deliveryAddress(deliveryAddress).build();
        ProductDelivery productDelivery = ProductDelivery.builder()
                .deliverySpecification(deliverySpecification)
                .startingAddress(startingAddress).status(Available).build();
        List<HandlingEvent> emptyList = new ArrayList<>();
        DeliveryHistory deliveryHistory = DeliveryHistory.builder().productDelivery(productDelivery).handlingEvents(emptyList).build();
        productDelivery.setDeliveryHistory(deliveryHistory);

        Vehicle vehicle = Vehicle.builder().vehicleCategory("Van").capacity(900.0).engine("2.0").brand("Renault").build();

        List<HandlingEvent> emptyList2 = new ArrayList<>();
        TransportMovement transportMovement = TransportMovement.builder().transportMovementId(1L)
                .startingAddress(startingAddress).deliverySpecification(deliverySpecification).handlingEvents(emptyList2)
                .vehicle(vehicle).build();

        HandlingEvent handlingEvent = HandlingEvent.builder().handlingEventId(1L)
                .transportMovement(transportMovement)
                .deliveryHistory(deliveryHistory).state(HandlingEventState.INITIALIZING_EVENT).build();

        HandlingEvent updatedHandlingEvent = HandlingEvent.builder().handlingEventId(1L)
                .transportMovement(transportMovement)
                .deliveryHistory(deliveryHistory).state(HandlingEventState.LOADING_EVENT).build();

        when(handlingEventRepository.findById(1L)).thenReturn(Optional.of(handlingEvent));
        HandlingEventDTO eventDTO = handlingEventService.updateHandlingEvent(updatedHandlingEvent);

        ArgumentCaptor<HandlingEvent> handlingEventArgumentCaptor = ArgumentCaptor.forClass(HandlingEvent.class);
        verify(handlingEventRepository).save(handlingEventArgumentCaptor.capture());
        HandlingEvent result = handlingEventArgumentCaptor.getValue();

        assertThat(result.getState()).isEqualTo(handlingEvent.getState());
    }

    @Test
    void removeHandlingEvent() {
        DeliveryAddress startingAddress = DeliveryAddress.builder().city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliveryAddress deliveryAddress = DeliveryAddress.builder().city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliverySpecification deliverySpecification = DeliverySpecification.builder().deliveryAddress(deliveryAddress).build();
        ProductDelivery productDelivery = ProductDelivery.builder()
                .deliverySpecification(deliverySpecification)
                .startingAddress(startingAddress).status(Available).build();
        List<HandlingEvent> emptyList = new ArrayList<>();
        DeliveryHistory deliveryHistory = DeliveryHistory.builder().productDelivery(productDelivery).handlingEvents(emptyList).build();
        productDelivery.setDeliveryHistory(deliveryHistory);

        Vehicle vehicle = Vehicle.builder().vehicleCategory("Van").capacity(900.0).engine("2.0").brand("Renault").build();

        List<HandlingEvent> emptyList2 = new ArrayList<>();
        TransportMovement transportMovement = TransportMovement.builder().transportMovementId(1L)
                .startingAddress(startingAddress).deliverySpecification(deliverySpecification).handlingEvents(emptyList2)
                .vehicle(vehicle).build();

        HandlingEvent handlingEvent = HandlingEvent.builder().handlingEventId(1L)
                .transportMovement(transportMovement)
                .deliveryHistory(deliveryHistory).state(HandlingEventState.INITIALIZING_EVENT).build();

        when(handlingEventRepository.findById(1L)).thenReturn(Optional.of(handlingEvent));

        handlingEventService.removeHandlingEvent(1L);
        verify(handlingEventRepository).delete(handlingEvent);
    }
}