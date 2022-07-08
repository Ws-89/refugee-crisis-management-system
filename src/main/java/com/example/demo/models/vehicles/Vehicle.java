package com.example.demo.models.vehicles;

import com.example.demo.models.productsdelivery.TransportMovement;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "tbl_vehicle")
@NamedEntityGraph(name="graph.VehicleTransportMovement", attributeNodes = {@NamedAttributeNode(value = "transportMovement")})
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
    @Column(name = "vehicle_id")
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
    private List<TransportMovement> transportMovement = new ArrayList<>();

    public Vehicle(Double capacity, String licensePlate) {
        this.capacity = capacity;
        this.licensePlate = licensePlate;
    }

    public void update(Vehicle source){
        this.vehicleId = source.getVehicleId();
        this.brand = source.getBrand();
        this.model = source.getModel();
        this.engine = source.getEngine();
        this.capacity = source.getCapacity();
        this.vehicleCategory = source.getVehicleCategory();
        this.licensePlate = source.getLicensePlate();
    };

    public void addTransportMovement(TransportMovement transportMovement){
        this.transportMovement.add(transportMovement);
        transportMovement.setVehicle(this);
    }

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
