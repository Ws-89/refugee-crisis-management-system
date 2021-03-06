package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.models.products.Status;
import com.example.demo.models.productsdelivery.*;
import com.example.demo.models.vehicles.Vehicle;
import com.example.demo.repo.HandlingEventRepository;
import com.example.demo.repo.ProductDeliveryRepository;
import com.example.demo.repo.TransportMovementRepo;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HandlingEventServiceImplementation implements HandlingEventService {

    private final ProductDeliveryRepository productDeliveryRepository;
    private final HandlingEventRepository handlingEventRepository;
    private final TransportMovementRepo transportMovementRepo;
    private final EntityManager em;

    public HandlingEventServiceImplementation(ProductDeliveryRepository productDeliveryRepository, HandlingEventRepository handlingEventRepository, TransportMovementRepo transportMovementRepo, EntityManager entityManager) {
        this.productDeliveryRepository = productDeliveryRepository;
        this.handlingEventRepository = handlingEventRepository;
        this.transportMovementRepo = transportMovementRepo;
        this.em = entityManager;
    }

    @Override
    public List<HandlingEvent> findAllByTransportMovementId(Long id) {
        return this.handlingEventRepository.findAllByTransportMovementId(id);
    }

    @Override
    public HandlingEvent getHandlingEvent(Long id) {
        return handlingEventRepository.findById(id).orElseThrow(() -> new NotFoundException("Handling event not found"));
    }


    @Override
    @Transactional
    public HandlingEvent saveHandlingEvent(HandlingEvent event, Long deliveryId, Long transportId) {
        ProductDelivery productDelivery = productDeliveryRepository.findById(deliveryId).orElseThrow(() -> new NotFoundException("Delivery not found"));
//        TransportMovement transportMovement = transportMovementRepo.findById(transportId).orElseThrow(() -> new NotFoundException("Transport not found"));

        EntityGraph<?> graph = em.getEntityGraph("graph.TransportMovementHandlingEvents");

        Map<String, Object> hints = new HashMap<String, Object>();
        hints.put("javax.persistence.fetchgraph", graph);

        TransportMovement transportMovement = null;

        try {
            transportMovement = em.find(TransportMovement.class, transportId, hints);
        }catch(HibernateException ex) {
            ex.printStackTrace();
        }
        System.out.println(transportMovement);
        HandlingEvent handlingEvent = HandlingEvent.builder()
                        .state(event.getState())
                        .timeStamp(event.getTimeStamp())
                                .build();

        productDelivery.getDeliveryHistory().addEvent(handlingEvent);

        productDelivery.setStatus(Status.Reserved);
        transportMovement.addHandlingEvent(handlingEvent);

        return handlingEventRepository.save(handlingEvent);
    }

    @Override
    public HandlingEvent updateHandlingEvent(HandlingEvent event) {
        return handlingEventRepository.findById(event.getHandlingEventId())
                .map(e -> {
                    e.setTransportMovement(event.getTransportMovement());
                    e.setDeliveryHistory(event.getDeliveryHistory());
                    e.setState(event.getState());
                    e.setTimeStamp(event.getTimeStamp());
                    return handlingEventRepository.save(e);
                }).orElseThrow(()-> new NotFoundException("Handling event not found"));
    }

    @Override
    public Long removeHandlingEvent(Long id) {

        return handlingEventRepository.findById(id)
                .map(e -> {
                    handlingEventRepository.delete(e);
                    return id;
                }).orElseThrow(()-> new NotFoundException("Handling event not found"));
    }
}
