package com.example.demo.models.vehicles;

import com.example.demo.models.productsdelivery.TransportMovement;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Table(name = "tbl_vehicle")
@Data
public abstract class Vehicle implements Serializable {

    @Id
    @SequenceGenerator(
            name = "vehicle_sequence",
            sequenceName = "vehicle_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vehicle_sequence"
    )
    private Long vehicleId;
    private Double capacity;
    private String vehicleCategory;
    private String licensePlate;
    @OneToMany(
            mappedBy = "vehicle"
    )
    private List<TransportMovement> transportMovement = new ArrayList<TransportMovement>();

    public Vehicle(Double capacity, String licensePlate) {
        this.capacity = capacity;
        this.licensePlate = licensePlate;
    }

    public void update(VehicleDTO vehicle){

    };
}
