package com.example.demo.models.vehicles;

import com.example.demo.models.productsdelivery.TransportMovement;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "transportMovementId")
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

    public void addTransportMovement(TransportMovement transportMovement){
        this.transportMovement.add(transportMovement);
        transportMovement.setVehicle(this);
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public String getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(String vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }


    public List<TransportMovement> getTransportMovement() {
        return transportMovement;
    }

    public void setTransportMovement(List<TransportMovement> transportMovement) {
        this.transportMovement = transportMovement;
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
