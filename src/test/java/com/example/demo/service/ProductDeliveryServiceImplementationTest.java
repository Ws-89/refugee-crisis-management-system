package com.example.demo.service;

import com.example.demo.models.products.*;
import com.example.demo.models.productsdelivery.DeliveryAddress;
import com.example.demo.models.productsdelivery.DeliveryHistory;
import com.example.demo.models.productsdelivery.DeliverySpecification;
import com.example.demo.models.productsdelivery.ProductDelivery;
import com.example.demo.repo.ProductDeliveryRepository;
import org.hibernate.Session;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
class ProductDeliveryServiceImplementationTest {

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @Transactional
    void shouldFindAllProductDeliveries() {



        EntityGraph<?> graph = entityManager.getEntityGraph("graph.DeliveryProduct");
        Map<String, Object> hints = new HashMap<String, Object>();

        hints.put("javax.persistence.fetchgraph", graph);

        ProductDelivery productDelivery = entityManager.find(ProductDelivery.class, 1L, hints);
        System.out.println(productDelivery);

        System.out.println(productDelivery.getProducts().size() + " ROZMIAR LISTY PRODUKTÓW");

    }


//
//    @InjectMocks
//    ProductDeliveryServiceImplementation productDeliveryServiceImplementation;
////        ProductDelivery productDelivery = entityManager.find(ProductDelivery.class, 1L, hints);
//    @Mock
//    ProductDeliveryRepository productDeliveryRepository;
//
//    @Test
//    void injectedComponentsAreNotNull(){
//        assertThat(productDeliveryServiceImplementation).isNotNull();
//
//        assertThat(productDeliveryRepository).isNotNull();
//    }
//
//    @Test
//    void getOne() {
//    }
//
//    @Test
//    void saveProductDelivery() {
//
//        FoodProduct CocaCola = new FoodProduct(
//                "Coca cola",
//                LocalDateTime.of(2025, Month.DECEMBER, 17, 0, 0),
//                "100x2L",
//                200.0,
//                100L,
//                Status.Available,
//                false,
//                State.Liquid,
//                FoodType.Drink
//                );
//        FoodProduct Lays = new FoodProduct(
//                "Lays",
//                LocalDateTime.of(2025, Month.DECEMBER, 17, 0, 0),
//                "100x300g",
//                30.0,
//                100L,
//                Status.Available,
//                false,
//                State.Solid,
//                FoodType.Snacks
//        );
//
//        Set<Product> products = new HashSet<>();
//        products.add(CocaCola);
//        products.add(Lays);
//
//
//
//        DeliveryHistory deliveryHistory = new DeliveryHistory();
//
//        DeliveryAddress deliveryAddress = DeliveryAddress.builder()
//                .city("Xyz")
//                .postCode("123")
//                .street("Qwe").build();
//
//        DeliverySpecification deliverySpecification = new DeliverySpecification();
//        deliverySpecification.setDeliveryAddress(deliveryAddress);
//
//
//        ProductDelivery productDelivery = ProductDelivery.builder()
//                .deliveryHistory(deliveryHistory)
//                .products(products)
//                .deliverySpecification(deliverySpecification)
//                .description("Some snacks")
//                .capacity(500.0)
//                .totalWeight(230.0)
//                .build();
//
////        productDeliveryServiceImplementation.saveProductDelivery(productDelivery);
//
//        ArgumentCaptor<ProductDelivery> productDeliveryCaptor = ArgumentCaptor.forClass(ProductDelivery.class);
//        verify(productDeliveryRepository).save(productDeliveryCaptor.capture());
//
//        ProductDelivery capturedProductDelivery = productDeliveryCaptor.getValue();
//
//        assertThat(capturedProductDelivery.getProducts()).isEqualTo(productDelivery.getProducts());
//        System.out.println(capturedProductDelivery.getProducts().toString());
//
//    }
//
//    @Test
//    void updateProductDelivery() {
//    }
//
//    @Test
//    void deleteProductDelivery() {
//    }
//
//    @Test
//    void findAllProductDeliveries() {
//    }
}