package com.example.demo.service;

import com.example.demo.dto.HandlingEventDTO;
import com.example.demo.mappers.HandlingEventMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.productsdelivery.*;
import com.example.demo.repo.HandlingEventRepository;
import com.example.demo.repo.ProductDeliveryRepository;
import com.example.demo.repo.TransportMovementRepo;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class HandlingEventServiceImplementation implements HandlingEventService {

    private final ProductDeliveryRepository productDeliveryRepository;
    private final HandlingEventRepository handlingEventRepository;
    private final TransportMovementRepo transportMovementRepo;
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private final EntityManager em;

    public static final String GET_ALL_HANDLING_EVENTS_BY_TRANSPORT_MOVEMENT_ID = "FROM HandlingEvent he WHERE he.transportMovement.transportMovementId = ?1";

    @Override
    public List<HandlingEventDTO> findAllByTransportMovementId(Long id) {
        EntityGraph<?> graph = em.getEntityGraph("graph.handlingEventTransportMovement");

        TypedQuery<HandlingEvent> query = em.createQuery(GET_ALL_HANDLING_EVENTS_BY_TRANSPORT_MOVEMENT_ID, HandlingEvent.class);
        query.setParameter(1, id);
        query.setHint("javax.persistence.fetchgraph", graph);
        return query.getResultList().stream().map(h -> HandlingEventMapper.INSTANCE.entityToDTO(h)).collect(Collectors.toList());
    }

    @Override
    public HandlingEventDTO getHandlingEvent(Long id) {
        return handlingEventRepository.findById(id)
                .map(h -> HandlingEventMapper.INSTANCE.entityToDTO(h)).orElseThrow(() -> new NotFoundException("Handling event not found"));
    }

    @Override
    @Transactional
    public HandlingEventDTO saveHandlingEvent(HandlingEvent event, Long deliveryId, Long transportId) {
        ProductDelivery productDelivery = productDeliveryRepository.findById(deliveryId).orElseThrow(() -> new NotFoundException("Delivery not found"));

        Boolean isAreadyInThisTransport = isAreadyInThisTransport(transportId, productDelivery);

        if(isAreadyInThisTransport){
            throw new IllegalStateException("This package has already been placed on this transport");

        }

        TransportMovement transportMovement = getTransportMovement(transportId);

        HandlingEvent handlingEvent = HandlingEvent.builder()
                        .state(event.getState())
                        .timeStamp(event.getTimeStamp())
                                .build();

        productDelivery.getDeliveryHistory().addEvent(handlingEvent);
        transportMovement.addHandlingEvent(handlingEvent);

        return HandlingEventMapper.INSTANCE.entityToDTO(handlingEventRepository.save(handlingEvent));
    }

    private TransportMovement getTransportMovement(Long transportId) {
        EntityGraph<?> graph = em.getEntityGraph("graph.TransportMovementHandlingEvents");

        Map<String, Object> hints = new HashMap<String, Object>();
        hints.put("javax.persistence.fetchgraph", graph);

        TransportMovement transportMovement = null;

        try {
            transportMovement = em.find(TransportMovement.class, transportId, hints);
        }catch(HibernateException ex) {
            ex.printStackTrace();
        }
        return transportMovement;
    }

    private boolean isAreadyInThisTransport(Long transportId, ProductDelivery productDelivery) {
        return productDelivery.getDeliveryHistory().getHandlingEvents().stream().filter(x -> x.getState() == HandlingEventState.INITIALIZING_EVENT)
                .anyMatch(x -> x.getTransportMovement().getTransportMovementId().equals(transportId));
    }

    @Override
    public HandlingEventDTO updateHandlingEvent(HandlingEvent event) {
        return handlingEventRepository.findById(event.getHandlingEventId())
                .map(e -> {
                    e.setTransportMovement(event.getTransportMovement());
                    e.setDeliveryHistory(event.getDeliveryHistory());
                    e.setState(event.getState());
                    e.setTimeStamp(event.getTimeStamp());
                    return HandlingEventMapper.INSTANCE.entityToDTO(handlingEventRepository.save(e));
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
