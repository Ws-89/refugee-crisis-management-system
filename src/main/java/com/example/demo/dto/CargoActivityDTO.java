package com.example.demo.dto;

import com.example.demo.models.cargo.CargoActivityCategory;
import com.example.demo.models.cargo.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoActivityDTO {

    private Long cargoActivityId;
    private LocalDateTime timeStamp;
    private Address address;
    private CargoActivityCategory cargoActivityCategory;
    private Long transportMovementId;
}
