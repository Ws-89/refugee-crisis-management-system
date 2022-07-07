package com.example.demo.service;

import com.example.demo.dto.DeliveryAddressDTO;
import com.example.demo.dto.DeliveryAddressMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.productsdelivery.DeliveryAddress;
import com.example.demo.repo.DeliveryAddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryAddressService {

    private final DeliveryAddressRepository deliveryAddressRepository;

    public DeliveryAddressService(DeliveryAddressRepository deliveryAddressRepository) {
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

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
        return deliveryAddressRepository.findById(deliveryAddress.getDeliveryAddressId())
                .map(a -> {
                    a.setCity(deliveryAddress.getCity());
                    a.setStreet(deliveryAddress.getStreet());
                    a.setPostCode(deliveryAddress.getPostCode());
                    a.setState(deliveryAddress.getState());
                    return DeliveryAddressMapper.INSTANCE.entityToDTO(deliveryAddressRepository.save(a));
                })
                .orElseThrow(() -> new NotFoundException("Addres not found"));
    }

    public Long delete(Long id){
        DeliveryAddress address = deliveryAddressRepository.findById(id).orElseThrow(() -> new NotFoundException("Addres not found"));
        deliveryAddressRepository.delete(address);
        return id;
    }

}
