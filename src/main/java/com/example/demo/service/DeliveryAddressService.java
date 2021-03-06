package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.models.productsdelivery.DeliveryAddress;
import com.example.demo.repo.DeliveryAddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAddressService {

    private final DeliveryAddressRepository deliveryAddressRepository;

    public DeliveryAddressService(DeliveryAddressRepository deliveryAddressRepository) {
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

    public DeliveryAddress findById(Long id){
        return deliveryAddressRepository.findById(id).orElseThrow(() -> new NotFoundException("Addres not found"));
    }

    public DeliveryAddress findByCity(String city){
        return deliveryAddressRepository.findByCity(city).orElseThrow(() -> new NotFoundException("Addres not found"));
    }

    public DeliveryAddress findByPostCode(String postCode){
        return deliveryAddressRepository.findByPostCode(postCode).orElseThrow(() -> new NotFoundException("Addres not found"));
    }

    public DeliveryAddress findByStreet(String street){
        return deliveryAddressRepository.findByStreet(street).orElseThrow(() -> new NotFoundException("Addres not found"));
    }

    public List<DeliveryAddress> findAll(){
        return deliveryAddressRepository.findAll();
    }

    public DeliveryAddress save(DeliveryAddress deliveryAddress){
        return deliveryAddressRepository.save(deliveryAddress);
    }

    public DeliveryAddress update(DeliveryAddress deliveryAddress){
        return deliveryAddressRepository.findById(deliveryAddress.getDeliveryAddressId())
                .map(a -> {
                    a.setCity(deliveryAddress.getCity());
                    a.setStreet(deliveryAddress.getStreet());
                    a.setPostCode(deliveryAddress.getPostCode());
                    a.setState(deliveryAddress.getState());
                    return deliveryAddressRepository.save(a);
                })
                .orElseThrow(() -> new NotFoundException("Addres not found"));
    }

    public Long delete(Long id){
        DeliveryAddress address = deliveryAddressRepository.findById(id).orElseThrow(() -> new NotFoundException("Addres not found"));
        deliveryAddressRepository.delete(address);
        return id;
    }

}
