package com.example.demo.service;

import com.example.demo.dto.DeliveryAddressDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.productsdelivery.DeliveryAddress;
import com.example.demo.repo.DeliveryAddressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeliveryAddressServiceTest {
    @Mock
    private DeliveryAddressRepository deliveryAddressRepository;
    @InjectMocks
    private DeliveryAddressService deliveryAddressService;

    @Test
    void shouldFindDeliveryAddressById() {
        DeliveryAddress address = DeliveryAddress.builder()
                .deliveryAddressId(1L).city("Xyz").street("Abcdef ghjkl").postCode("12-345").state("Poiuytrwq Lkjhgfdsa").build();
        when(deliveryAddressRepository.findById(1L)).thenReturn(Optional.of(address));

        DeliveryAddressDTO addressDTO = deliveryAddressService.findById(1L);

        assertThat(addressDTO.getCity()).isEqualTo(address.getCity());
        assertThat(addressDTO.getStreet()).isEqualTo(address.getStreet());
        assertThat(addressDTO.getState()).isEqualTo(address.getState());
        assertThat(addressDTO.getPostCode()).isEqualTo(address.getPostCode());
    }

    @Test
    void shouldNotFindDeliveryAddressById() {
        when(deliveryAddressRepository.findById(2L)).thenThrow( new NotFoundException("Addres not found"));

        assertThrows(NotFoundException.class, () -> deliveryAddressRepository.findById(2L));
    }

    @Test
    void shouldReturnListOf4() {

        DeliveryAddress address = DeliveryAddress.builder()
                .deliveryAddressId(1L).city("Xyz").street("Abcdef ghjkl").postCode("12-345").state("Poiuytrwq Lkjhgfdsa").build();
        DeliveryAddress address2 = DeliveryAddress.builder()
                .deliveryAddressId(1L).city("Xyz").street("Zxcvb ghjkl").postCode("12-345").state("Poiuytrwq Lkjhgfdsa").build();
        DeliveryAddress address3 = DeliveryAddress.builder()
                .deliveryAddressId(1L).city("Xyz").street("Abcdef ghjkl").postCode("23-543").state("Poiuytrwq Lkjhgfdsa").build();
        DeliveryAddress address4 = DeliveryAddress.builder()
                .deliveryAddressId(1L).city("Xyz").street("Abcdef ghjkl").postCode("12-345").state("Poiuytrwq Zxcvby").build();

        List<DeliveryAddress> adressess =  Arrays.asList(address,address2,address3,address4);

        when(deliveryAddressRepository.findAll()).thenReturn(adressess);

        List<DeliveryAddressDTO> list = deliveryAddressService.findAll();

        assertThat(list.size()).isEqualTo(4);
    }

    @Test
    void shouldReturnListOf2() {

        DeliveryAddress address = DeliveryAddress.builder()
                .deliveryAddressId(1L).city("Xyz").street("Abcdef ghjkl").postCode("12-345").state("Poiuytrwq Lkjhgfdsa").build();
        DeliveryAddress address2 = DeliveryAddress.builder()
                .deliveryAddressId(1L).city("Xyz").street("Zxcvb ghjkl").postCode("12-345").state("Poiuytrwq Lkjhgfdsa").build();


        List<DeliveryAddress> adressess =  Arrays.asList(address,address2);

        when(deliveryAddressRepository.findAll()).thenReturn(adressess);

        List<DeliveryAddressDTO> list = deliveryAddressService.findAll();

        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void shouldSaveDeliveryAddress() {
        DeliveryAddress address = DeliveryAddress.builder()
                .deliveryAddressId(1L).city("Xyz").street("Abcdef ghjkl").postCode("12-345").state("Poiuytrwq Lkjhgfdsa").build();
        when(deliveryAddressRepository.save(Mockito.any(DeliveryAddress.class)))
                .thenReturn(address);

        DeliveryAddressDTO deliveryAddressDTO = deliveryAddressService.save(address);

        assertThat(deliveryAddressDTO.getDeliveryAddressId()).isEqualTo(address.getDeliveryAddressId());
        assertThat(deliveryAddressDTO.getStreet()).isEqualTo(address.getStreet());
        assertThat(deliveryAddressDTO.getState()).isEqualTo(address.getState());
        assertThat(deliveryAddressDTO.getPostCode()).isEqualTo(address.getPostCode());
    }

    @Test
    void shouldUpdateDeliveryAddress() {
        DeliveryAddress address = DeliveryAddress.builder()
                .deliveryAddressId(1L).city("Xyz").street("Abcdef ghjkl").postCode("12-345").state("Poiuytrwq Lkjhgfdsa").build();
        DeliveryAddress updatedAddress = DeliveryAddress.builder()
                .deliveryAddressId(1L).city("XyzQ").street("AbcdefQ ghjkl").postCode("12-345Q").state("Poiuytrwq LkjhgfdsaQ").build();
        when(deliveryAddressRepository.findById(1L)).thenReturn(Optional.of(address));

        DeliveryAddressDTO updatedAddressDTO = deliveryAddressService.update(updatedAddress);

        ArgumentCaptor<DeliveryAddress> deliveryAddressArgumentCaptor = ArgumentCaptor.forClass(DeliveryAddress.class);
        verify(deliveryAddressRepository).save(deliveryAddressArgumentCaptor.capture());
        DeliveryAddress deliveryAddressArgumentCaptorValue = deliveryAddressArgumentCaptor.getValue();

        assertThat(deliveryAddressArgumentCaptorValue.getDeliveryAddressId()).isEqualTo(updatedAddress.getDeliveryAddressId());
        assertThat(deliveryAddressArgumentCaptorValue.getState()).isEqualTo(updatedAddress.getState());
        assertThat(deliveryAddressArgumentCaptorValue.getCity()).isEqualTo(updatedAddress.getCity());
        assertThat(deliveryAddressArgumentCaptorValue.getStreet()).isEqualTo(updatedAddress.getStreet());
        assertThat(deliveryAddressArgumentCaptorValue.getPostCode()).isEqualTo(updatedAddress.getPostCode());
    }

    @Test
    void shouldDeleteDeliveredAddress() {
        DeliveryAddress address = DeliveryAddress.builder()
                .deliveryAddressId(1L).city("Xyz").street("Abcdef ghjkl").postCode("12-345").state("Poiuytrwq Lkjhgfdsa").build();
        when(deliveryAddressRepository.findById(1L)).thenReturn(Optional.of(address));

        deliveryAddressService.delete(address.getDeliveryAddressId());

        verify(deliveryAddressRepository).delete(address);
    }

    @Test
    void deleteShouldThrowException() {
        DeliveryAddress address = DeliveryAddress.builder()
                .deliveryAddressId(1L).city("Xyz").street("Abcdef ghjkl").postCode("12-345").state("Poiuytrwq Lkjhgfdsa").build();

        when(deliveryAddressRepository.findById(1L)).thenThrow(new NotFoundException("Addres not found"));

        assertThrows(NotFoundException.class, () -> deliveryAddressService.delete(address.getDeliveryAddressId()));
    }
}