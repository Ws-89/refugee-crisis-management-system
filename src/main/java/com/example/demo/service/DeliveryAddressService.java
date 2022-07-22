package com.example.demo.service;

import com.example.demo.dto.DeliveryAddressDTO;
import com.example.demo.mappers.DeliveryAddressMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.cargo.DeliveryAddress;
import com.example.demo.repo.DeliveryAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryAddressService {

    private final DeliveryAddressRepository deliveryAddressRepository;

    public DeliveryAddressDTO findById(Long id){
        return deliveryAddressRepository.findById(id)
                .map(a -> DeliveryAddressMapper.INSTANCE.entityToDTO(a)).orElseThrow(() -> new NotFoundException("Addres not found"));
    }

    public DeliveryAddressDTO findByCity(String city){
        return deliveryAddressRepository.findByCity(city)
                .map(a -> DeliveryAddressMapper.INSTANCE.entityToDTO(a)).orElseThrow(() -> new NotFoundException("Addres not found"));
    }

    public DeliveryAddressDTO findByPostCode(String postCode){
        return deliveryAddressRepository.findByPostCode(postCode)
                .map(a -> DeliveryAddressMapper.INSTANCE.entityToDTO(a)).orElseThrow(() -> new NotFoundException("Addres not found"));
    }

    public DeliveryAddressDTO findByStreet(String street){
        return deliveryAddressRepository.findByStreet(street)
                .map(a -> DeliveryAddressMapper.INSTANCE.entityToDTO(a)).orElseThrow(() -> new NotFoundException("Addres not found"));
    }

    public List<DeliveryAddressDTO> findAll(){
        return deliveryAddressRepository.findAll().stream().map(a -> DeliveryAddressMapper.INSTANCE.entityToDTO(a)).collect(Collectors.toList());
    }

    public DeliveryAddressDTO save(DeliveryAddress deliveryAddress){
        return DeliveryAddressMapper.INSTANCE.entityToDTO(deliveryAddressRepository.save(deliveryAddress));
    }

    public DeliveryAddressDTO update(DeliveryAddress deliveryAddress){
        DeliveryAddress result  = deliveryAddressRepository.findById(deliveryAddress.getDeliveryAddressId())
                .map(a -> {
                        DeliveryAddress address = a;
                    address.setCity(deliveryAddress.getCity());
                    address.setStreet(deliveryAddress.getStreet());
                    address.setPostCode(deliveryAddress.getPostCode());
                    address.setState(deliveryAddress.getState());
                    return address;
                })
                .orElseThrow(() -> new NotFoundException("Addres not found"));
        return DeliveryAddressMapper.INSTANCE.entityToDTO(deliveryAddressRepository.save(result));
    }

    public Long delete(Long id){
        DeliveryAddress address = deliveryAddressRepository.findById(id).orElseThrow(() -> new NotFoundException("Addres not found"));
        deliveryAddressRepository.delete(address);
        return id;
    }

}
