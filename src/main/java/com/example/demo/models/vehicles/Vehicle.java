package com.example.demo.models.vehicles;

import com.example.demo.models.productsdelivery.TransportMovement;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_vehicle")
@Data
public class Vehicle implements Serializable, Comparable<Vehicle> {

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
    private String brand;
    private String model;
    private String engine;
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

    public void update(VehicleDTO source){
        this.vehicleId = source.getVehicleId();
        this.brand = source.getBrand();
        this.model = source.getModel();
        this.engine = source.getEngine();
        this.capacity = source.getCapacity();
        this.vehicleCategory = source.getVehicleCategory();
        this.licensePlate = source.getLicensePlate();
    };

    @Override
    public int compareTo(Vehicle o) {
        return getCapacity().compareTo(o.getCapacity());
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", capacity=" + capacity +
                ", vehicleCategory='" + vehicleCategory + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", transportMovement=" + transportMovement +
                '}';
    }
}
