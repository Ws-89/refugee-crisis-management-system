package com.example.demo.models.vehicles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class VehicleDTO {

     private Long vehicleId;
     private String brand;
     private String model;
     private String engine;
     private Double capacity;
     private String vehicleCategory;
     private String licensePlate;

     public VehicleDTO(Long vehicleId, String brand, String model, String engine, Double capacity, String vehicleCategory, String licensePlate) {
          this.vehicleId = vehicleId;
          this.brand = brand;
          this.model = model;
          this.engine = engine;
          this.capacity = capacity;
          this.vehicleCategory = vehicleCategory;
          this.licensePlate = licensePlate;
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
}
