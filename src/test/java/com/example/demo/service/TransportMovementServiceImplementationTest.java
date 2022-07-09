package com.example.demo.service;

import com.example.demo.dto.TransportMovementDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.productsdelivery.*;
import com.example.demo.models.vehicles.Vehicle;
import com.example.demo.repo.DeliveryAddressRepository;
import com.example.demo.repo.TransportMovementRepo;
import com.example.demo.repo.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransportMovementServiceImplementationTest {

    @Mock
    private TransportMovementRepo transportMovementRepo;
    @Mock
    private DeliveryAddressRepository deliveryAddressRepository;
    @Mock
    private VehicleRepository vehicleRepository;
    @Mock
    private EntityManager entityManagerMock;

    @InjectMocks
    TransportMovementServiceImplementation transportMovementService;

    @Test
    void shouldFindTransportMovementById() {

        DeliveryAddress startingAddress = DeliveryAddress.builder().deliveryAddressId(1L).city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliveryAddress deliveryAddress = DeliveryAddress.builder().city("Qwerty").deliveryAddressId(2L).postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliverySpecification deliverySpecification = DeliverySpecification.builder().deliveryAddress(deliveryAddress).build();
        List<HandlingEvent> handlingEvents = Arrays.asList(HandlingEvent.builder().state(HandlingEventState.INITIALIZING_EVENT).build());
        Vehicle vehicle = Vehicle.builder().vehicleCategory("Van").capacity(900.0).engine("2.0").brand("Renault").build();
        TransportMovement transportMovement = TransportMovement.builder()
                .transportMovementId(1L).handlingEvents(handlingEvents).deliverySpecification(deliverySpecification)
                .startingAddress(startingAddress).vehicle(vehicle).build();

        when(transportMovementRepo.findById(1L)).thenReturn(Optional.of(transportMovement));

        TransportMovementDTO transportMovementDTO = transportMovementService.findById(1L);

        assertThat(transportMovementDTO.getVehicle().getCapacity()).isEqualTo(transportMovement.getVehicle().getCapacity());
        assertThat(transportMovementDTO.getDeliverySpecification().getDeliveryAddress().getStreet()).isEqualTo(transportMovement.getDeliverySpecification().getDeliveryAddress().getStreet());
        assertThat(transportMovementDTO.getStartingAddress().getCity()).isEqualTo(transportMovement.getStartingAddress().getCity());
    }

    @Test
    void transportMovementFindByIdShouldThrowException(){
        when(transportMovementRepo.findById(1L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> transportMovementService.findById(1L));
    }

    @Test
    void shouldSaveTransportMovement() {
        DeliveryAddress startingAddress = DeliveryAddress.builder().deliveryAddressId(1L).city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliveryAddress deliveryAddress = DeliveryAddress.builder().city("Qwerty").deliveryAddressId(2L).postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliverySpecification deliverySpecification = DeliverySpecification.builder().deliveryAddress(deliveryAddress).build();
        List<HandlingEvent> handlingEvents = Arrays.asList(HandlingEvent.builder().state(HandlingEventState.INITIALIZING_EVENT).build());
        List<TransportMovement> transportMovements = new ArrayList<>();
        Vehicle vehicle = Vehicle.builder().vehicleId(1L).vehicleCategory("Van").transportMovement(transportMovements).capacity(900.0).engine("2.0").brand("Renault").build();
        TransportMovement transportMovement = TransportMovement.builder()
                .transportMovementId(1L).handlingEvents(handlingEvents).deliverySpecification(deliverySpecification)
                .startingAddress(startingAddress).vehicle(vehicle).build();

        when(deliveryAddressRepository.findById(1L)).thenReturn(Optional.of(startingAddress));
        when(deliveryAddressRepository.findById(2L)).thenReturn(Optional.of(deliveryAddress));
        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));

        transportMovementService.save(transportMovement);
        ArgumentCaptor<TransportMovement> transportMovementArgumentCaptor = ArgumentCaptor.forClass(TransportMovement.class);
        verify(transportMovementRepo).save(transportMovementArgumentCaptor.capture());
        TransportMovement transportMovementArgumentCaptorValue = transportMovementArgumentCaptor.getValue();

        assertThat(transportMovementArgumentCaptorValue.getVehicle().getCapacity()).isEqualTo(vehicle.getCapacity());
        assertThat(transportMovement.getDeliverySpecification().getDeliveryAddress().getStreet())
                .isEqualTo(deliveryAddress.getStreet());
    }

    @Test
    void saveTransportMovementShouldThrowException(){
        DeliveryAddress startingAddress = DeliveryAddress.builder().deliveryAddressId(1L).city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliveryAddress deliveryAddress = DeliveryAddress.builder().city("Qwerty").deliveryAddressId(2L).postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliverySpecification deliverySpecification = DeliverySpecification.builder().deliveryAddress(deliveryAddress).build();
        List<HandlingEvent> handlingEvents = Arrays.asList(HandlingEvent.builder().state(HandlingEventState.INITIALIZING_EVENT).build());
        List<TransportMovement> transportMovements = new ArrayList<>();
        Vehicle vehicle = Vehicle.builder().vehicleId(1L).vehicleCategory("Van").transportMovement(transportMovements).capacity(900.0).engine("2.0").brand("Renault").build();
        TransportMovement transportMovement = TransportMovement.builder()
                .transportMovementId(1L).handlingEvents(handlingEvents).deliverySpecification(deliverySpecification)
                .startingAddress(startingAddress).vehicle(vehicle).build();

        when(deliveryAddressRepository.findById(1L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> transportMovementService.save(transportMovement));
    }

    @Test
    void saveTransportMovementShouldThrowVehicleNotFoundException() {
        DeliveryAddress startingAddress = DeliveryAddress.builder().deliveryAddressId(1L).city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliveryAddress deliveryAddress = DeliveryAddress.builder().city("Qwerty").deliveryAddressId(2L).postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliverySpecification deliverySpecification = DeliverySpecification.builder().deliveryAddress(deliveryAddress).build();
        List<HandlingEvent> handlingEvents = Arrays.asList(HandlingEvent.builder().state(HandlingEventState.INITIALIZING_EVENT).build());
        List<TransportMovement> transportMovements = new ArrayList<>();
        Vehicle vehicle = Vehicle.builder().vehicleId(1L).vehicleCategory("Van").transportMovement(transportMovements).capacity(900.0).engine("2.0").brand("Renault").build();
        TransportMovement transportMovement = TransportMovement.builder()
                .transportMovementId(1L).handlingEvents(handlingEvents).deliverySpecification(deliverySpecification)
                .startingAddress(startingAddress).vehicle(vehicle).build();

        when(deliveryAddressRepository.findById(1L)).thenReturn(Optional.of(startingAddress));
        when(deliveryAddressRepository.findById(2L)).thenReturn(Optional.of(deliveryAddress));
        when(vehicleRepository.findById(1L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> transportMovementService.save(transportMovement));
    }

    @Test
    void delete() {
        DeliveryAddress startingAddress = DeliveryAddress.builder().deliveryAddressId(1L).city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliveryAddress deliveryAddress = DeliveryAddress.builder().city("Qwerty").deliveryAddressId(2L).postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliverySpecification deliverySpecification = DeliverySpecification.builder().deliveryAddress(deliveryAddress).build();
        List<HandlingEvent> handlingEvents = Arrays.asList(HandlingEvent.builder().state(HandlingEventState.INITIALIZING_EVENT).build());
        List<TransportMovement> transportMovements = new ArrayList<>();
        Vehicle vehicle = Vehicle.builder().vehicleId(1L).vehicleCategory("Van").transportMovement(transportMovements).capacity(900.0).engine("2.0").brand("Renault").build();
        TransportMovement transportMovement = TransportMovement.builder()
                .transportMovementId(1L).handlingEvents(handlingEvents).deliverySpecification(deliverySpecification)
                .startingAddress(startingAddress).vehicle(vehicle).build();

        when(transportMovementRepo.findById(1L)).thenReturn(Optional.of(transportMovement));
        transportMovementService.delete(1L);
        verify(transportMovementRepo).delete(transportMovement);


    }

    @Test
    void update() {
        DeliveryAddress startingAddress = DeliveryAddress.builder().deliveryAddressId(1L).city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliveryAddress deliveryAddress = DeliveryAddress.builder().city("Qwerty").deliveryAddressId(2L).postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliverySpecification deliverySpecification = DeliverySpecification.builder().deliveryAddress(deliveryAddress).build();
        List<HandlingEvent> handlingEvents = Arrays.asList(HandlingEvent.builder().state(HandlingEventState.INITIALIZING_EVENT).build());
        List<TransportMovement> transportMovements = new ArrayList<>();
        Vehicle vehicle = Vehicle.builder().vehicleId(1L).vehicleCategory("Van").transportMovement(transportMovements).capacity(900.0).engine("2.0").brand("Renault").build();
        TransportMovement transportMovement = TransportMovement.builder()
                .transportMovementId(1L).handlingEvents(handlingEvents).deliverySpecification(deliverySpecification)
                .startingAddress(startingAddress).vehicle(vehicle).build();

        DeliveryAddress updatedStartingAddress = DeliveryAddress.builder().deliveryAddressId(1L).city("QwertyQ").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliveryAddress updatedDeliveryAddress = DeliveryAddress.builder().city("Qwerty").deliveryAddressId(2L).postCode("12-345").state("ZxQcv").street("Fghjk").build();
        DeliverySpecification updatedDeliverySpecification = DeliverySpecification.builder().deliveryAddress(updatedDeliveryAddress).build();
        List<HandlingEvent> updatedHandlingEvents = Arrays.asList(HandlingEvent.builder().state(HandlingEventState.INITIALIZING_EVENT).build());
        List<TransportMovement> updatedTransportMovements = new ArrayList<>();
        Vehicle updatedVehicle = Vehicle.builder().vehicleId(1L).vehicleCategory("VanQ").transportMovement(updatedTransportMovements).capacity(900.0).engine("2.0").brand("Renault").build();
        TransportMovement updatedTransportMovement = TransportMovement.builder()
                .transportMovementId(1L).handlingEvents(updatedHandlingEvents).deliverySpecification(updatedDeliverySpecification)
                .startingAddress(updatedStartingAddress).vehicle(updatedVehicle).build();

        when(transportMovementRepo.findById(1L)).thenReturn(Optional.of(transportMovement));
        transportMovementService.update(updatedTransportMovement);

        ArgumentCaptor<TransportMovement> transportMovementArgumentCaptor = ArgumentCaptor.forClass(TransportMovement.class);
        verify(transportMovementRepo).save(transportMovementArgumentCaptor.capture());
        TransportMovement result = transportMovementArgumentCaptor.getValue();

        assertThat(result.getStartingAddress().getCity()).isEqualTo(updatedStartingAddress.getCity());
        assertThat(result.getDeliverySpecification().getDeliveryAddress().getState()).isEqualTo(updatedDeliveryAddress.getState());
        assertThat(result.getVehicle().getVehicleCategory()).isEqualTo(updatedVehicle.getVehicleCategory());



    }

    @Test
    void findAll() {
        TransportMovement transportMovement = new TransportMovement();
        TransportMovement transportMovement2 = new TransportMovement();
        TransportMovement transportMovement3 = new TransportMovement();
        TransportMovement transportMovement4 = new TransportMovement();

        List<TransportMovement> transportMovementList = Arrays.asList(transportMovement, transportMovement2, transportMovement3, transportMovement4);
        TypedQuery queryMock = Mockito.mock(TypedQuery.class);
        when(queryMock.getResultList()).thenReturn(transportMovementList);

        when(entityManagerMock.createQuery(TransportMovementServiceImplementation.GET_ALL_TRANSPORT_MOVEMENTS, TransportMovement.class)).thenReturn(queryMock);
        List<TransportMovementDTO> transportMovementDTOList = transportMovementService.findAll();

        assertThat(transportMovementDTOList.size()).isEqualTo(4);
    }
}