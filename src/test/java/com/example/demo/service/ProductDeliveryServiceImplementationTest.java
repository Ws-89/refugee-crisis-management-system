//package com.example.demo.service;
//
//import com.example.demo.models.products.*;
//import com.example.demo.models.productsdelivery.DeliveryAddress;
//import com.example.demo.models.productsdelivery.DeliveryHistory;
//import com.example.demo.models.productsdelivery.DeliverySpecification;
//import com.example.demo.models.productsdelivery.ProductDelivery;
//import com.example.demo.repo.ProductDeliveryRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDateTime;
//import java.time.Month;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.Mockito.verify;
//
//@SpringBootTest
//class ProductDeliveryServiceImplementationTest {
//
//    @InjectMocks
//    ProductDeliveryServiceImplementation productDeliveryServiceImplementation;
//
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
//}