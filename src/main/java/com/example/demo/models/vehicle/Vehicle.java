package com.example.demo.models.vehicle;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "VEHICLE_TYPE",
        discriminatorType = DiscriminatorType.STRING
)
@RequiredArgsConstructor
@Table(name = "tbl_vehicle")
@Data
public abstract class Vehicle {





}
