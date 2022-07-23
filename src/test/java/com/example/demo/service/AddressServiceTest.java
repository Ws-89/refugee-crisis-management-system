package com.example.demo.service;

import com.example.demo.dto.AddressDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.cargo.Address;
import com.example.demo.repo.AddressRepository;
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
class AddressServiceTest {
    @Mock
    private AddressRepository addressRepository;
    @InjectMocks
    private AddressService addressService;

    @Test
    void shouldFindDeliveryAddressById() {
        Address address = Address.builder()
                .addressId(1L).city("Xyz").street("Abcdef ghjkl").postCode("12-345").state("Poiuytrwq Lkjhgfdsa").build();
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        AddressDTO addressDTO = addressService.findById(1L);

        assertThat(addressDTO.getCity()).isEqualTo(address.getCity());
        assertThat(addressDTO.getStreet()).isEqualTo(address.getStreet());
        assertThat(addressDTO.getState()).isEqualTo(address.getState());
        assertThat(addressDTO.getPostCode()).isEqualTo(address.getPostCode());
    }

    @Test
    void shouldNotFindDeliveryAddressById() {
        when(addressRepository.findById(2L)).thenThrow( new NotFoundException("Addres not found"));

        assertThrows(NotFoundException.class, () -> addressRepository.findById(2L));
    }

//    @Test
//    void shouldReturnListOf4() {
//
//        Address address = Address.builder()
//                .addressId(1L).city("Xyz").street("Abcdef ghjkl").postCode("12-345").state("Poiuytrwq Lkjhgfdsa").build();
//        Address address2 = Address.builder()
//                .addressId(1L).city("Xyz").street("Zxcvb ghjkl").postCode("12-345").state("Poiuytrwq Lkjhgfdsa").build();
//        Address address3 = Address.builder()
//                .addressId(1L).city("Xyz").street("Abcdef ghjkl").postCode("23-543").state("Poiuytrwq Lkjhgfdsa").build();
//        Address address4 = Address.builder()
//                .addressId(1L).city("Xyz").street("Abcdef ghjkl").postCode("12-345").state("Poiuytrwq Zxcvby").build();
//
//        List<Address> adressess =  Arrays.asList(address,address2,address3,address4);
//
//        when(addressRepository.findAll()).thenReturn(adressess);
//
//        List<AddressDTO> list = addressService.findAll();
//
//        assertThat(list.size()).isEqualTo(4);
//    }

//    @Test
//    void shouldReturnListOf2() {
//
//        Address address = Address.builder()
//                .addressId(1L).city("Xyz").street("Abcdef ghjkl").postCode("12-345").state("Poiuytrwq Lkjhgfdsa").build();
//        Address address2 = Address.builder()
//                .addressId(1L).city("Xyz").street("Zxcvb ghjkl").postCode("12-345").state("Poiuytrwq Lkjhgfdsa").build();
//
//
//        List<Address> adressess =  Arrays.asList(address,address2);
//
//        when(addressRepository.findAll()).thenReturn(adressess);
//
//        List<AddressDTO> list = addressService.findAll();
//
//        assertThat(list.size()).isEqualTo(2);
//    }

    @Test
    void shouldSaveDeliveryAddress() {
        Address address = Address.builder()
                .addressId(1L).city("Xyz").street("Abcdef ghjkl").postCode("12-345").state("Poiuytrwq Lkjhgfdsa").build();
        when(addressRepository.save(Mockito.any(Address.class)))
                .thenReturn(address);

        AddressDTO addressDTO = addressService.save(address);

        assertThat(addressDTO.getAddressId()).isEqualTo(address.getAddressId());
        assertThat(addressDTO.getStreet()).isEqualTo(address.getStreet());
        assertThat(addressDTO.getState()).isEqualTo(address.getState());
        assertThat(addressDTO.getPostCode()).isEqualTo(address.getPostCode());
    }

    @Test
    void shouldUpdateDeliveryAddress() {
        Address address = Address.builder()
                .addressId(1L).city("Xyz").street("Abcdef ghjkl").postCode("12-345").state("Poiuytrwq Lkjhgfdsa").build();
        Address updatedAddress = Address.builder()
                .addressId(1L).city("XyzQ").street("AbcdefQ ghjkl").postCode("12-345Q").state("Poiuytrwq LkjhgfdsaQ").build();
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        addressService.update(updatedAddress);

        ArgumentCaptor<Address> deliveryAddressArgumentCaptor = ArgumentCaptor.forClass(Address.class);
        verify(addressRepository).save(deliveryAddressArgumentCaptor.capture());
        Address addressArgumentCaptorValue = deliveryAddressArgumentCaptor.getValue();

        assertThat(addressArgumentCaptorValue.getAddressId()).isEqualTo(updatedAddress.getAddressId());
        assertThat(addressArgumentCaptorValue.getState()).isEqualTo(updatedAddress.getState());
        assertThat(addressArgumentCaptorValue.getCity()).isEqualTo(updatedAddress.getCity());
        assertThat(addressArgumentCaptorValue.getStreet()).isEqualTo(updatedAddress.getStreet());
        assertThat(addressArgumentCaptorValue.getPostCode()).isEqualTo(updatedAddress.getPostCode());
    }

    @Test
    void shouldDeleteDeliveredAddress() {
        Address address = Address.builder()
                .addressId(1L).city("Xyz").street("Abcdef ghjkl").postCode("12-345").state("Poiuytrwq Lkjhgfdsa").build();
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        addressService.delete(address.getAddressId());

        verify(addressRepository).delete(address);
    }

    @Test
    void deleteShouldThrowException() {
        Address address = Address.builder()
                .addressId(1L).city("Xyz").street("Abcdef ghjkl").postCode("12-345").state("Poiuytrwq Lkjhgfdsa").build();

        when(addressRepository.findById(1L)).thenThrow(new NotFoundException("Addres not found"));

        assertThrows(NotFoundException.class, () -> addressService.delete(address.getAddressId()));
    }
}