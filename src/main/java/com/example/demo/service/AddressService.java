package com.example.demo.service;

import com.example.demo.dto.AddressDTO;
import com.example.demo.mappers.AddressMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.cargo.Address;
import com.example.demo.repo.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressDTO findById(Long id){
        return addressRepository.findById(id)
                .map(a -> AddressMapper.INSTANCE.entityToDTO(a)).orElseThrow(() -> new NotFoundException("Addres not found"));
    }

    public Page<AddressDTO> findByCityContaining(String city, int page, int size){
        return addressRepository.findByCityContaining(city, PageRequest.of(page, size)).map(a -> AddressMapper.INSTANCE.entityToDTO(a));
    }

    public AddressDTO save(Address address){
        return AddressMapper.INSTANCE.entityToDTO(addressRepository.save(address));
    }

    public AddressDTO update(Address address){
        Address result  = addressRepository.findById(address.getAddressId())
                .map(a -> {
                        Address updatedAddress = a;
                    updatedAddress.setCity(address.getCity());
                    updatedAddress.setStreet(address.getStreet());
                    updatedAddress.setPostCode(address.getPostCode());
                    updatedAddress.setState(address.getState());
                    return updatedAddress;
                })
                .orElseThrow(() -> new NotFoundException("Addres not found"));
        return AddressMapper.INSTANCE.entityToDTO(addressRepository.save(result));
    }

    public Long delete(Long id){
        Address address = addressRepository.findById(id).orElseThrow(() -> new NotFoundException("Addres not found"));
        addressRepository.delete(address);
        return id;
    }

}
