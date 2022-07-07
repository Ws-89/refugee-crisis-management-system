package com.example.demo.dto;

public record DeliveryAddressDTO(Long deliveryAddressId,
                                 String postCode,
                                 String city,
                                 String street,
                                 String state) { }
