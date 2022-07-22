package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.models.cargo.CargoActivity;
import com.example.demo.models.cargo.CargoActivityCategory;
import com.example.demo.models.cargo.DeliveryHistory;
import com.example.demo.repo.CargoActivityRepository;
import com.example.demo.repo.DeliveryHistoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CargoActivityService {

    private CargoActivityRepository cargoActivityRepository;
    private DeliveryHistoryRepository deliveryHistoryRepository;

    public CargoActivityService(CargoActivityRepository cargoActivityRepository, DeliveryHistoryRepository deliveryHistoryRepository) {
        this.cargoActivityRepository = cargoActivityRepository;

        this.deliveryHistoryRepository = deliveryHistoryRepository;
    }

    @Transactional
    public Long addTransportActivity(Long deliveryHistoryId, CargoActivity activity){
        DeliveryHistory deliveryHistory = deliveryHistoryRepository.findById(deliveryHistoryId)
                .orElseThrow(() -> new NotFoundException("Cargo not found"));



        deliveryHistory.getCargoActivityList().add(activity);
        if(activity.getCargoActivityCategory() == CargoActivityCategory.Loaded){
            deliveryHistory.setIsLoaded(true);
        }else{
            deliveryHistory.setIsLoaded(false);
        }
        activity.setDeliveryHistory(deliveryHistory);
        CargoActivity cargoActivity = cargoActivityRepository.save(activity);
        return cargoActivity.getCargoActivityId();
    }


}
