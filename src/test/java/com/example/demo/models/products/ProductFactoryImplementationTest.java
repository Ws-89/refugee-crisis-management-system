//package com.example.demo.models.products;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDateTime;
//
//import static com.example.demo.models.products.FoodType.Drink;
//import static com.example.demo.models.products.State.Solid;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//class ProductFactoryImplementationTest {

//    @Autowired
//    private ProductFactoryImplementation productFactoryImplementation;
//
//    @Test
//    void getFoodProductInstance() {
//        ProductDTO foodProduct = ProductDTO.builder()
//                .name("Coffee")
//                .expirationDate(LocalDateTime.now())
//                .description("Black and strong")
//                .amount(200L)
//                .weight(200)
//                .fragile(false)
//                .state(Solid)
//                .foodType(Drink)
//                .productType(ProductType.Food)
//                .build();
//
//        assertThat(productFactoryImplementation.getInstance(foodProduct)).isInstanceOf(FoodProduct.class);
//    };
//
//    @Test
//    void getMedicalProductInstance() {
//        ProductDTO medicalProduct = ProductDTO.builder()
//                .name("Ibuprofen")
//                .expirationDate(LocalDateTime.now())
//                .description("anti-inflammatory")
//                .amount(2000L)
//                .weight(2)
//                .fragile(false)
//                .state(Solid)
//                .medicalPurpose(MedicalPurpose.Drug)
//                .productType(ProductType.Medical)
//                .build();
//
//        assertThat(productFactoryImplementation.getInstance(medicalProduct)).isInstanceOf(MedicalProduct.class);
//    };
//
//    @Test
//    void getHygieneProductInstance() {
//        ProductDTO hygieneProduct = ProductDTO.builder()
//                .name("Wizir")
//                .expirationDate(LocalDateTime.now())
//                .description("For white clothes")
//                .amount(200L)
//                .weight(400)
//                .fragile(false)
//                .state(Solid)
//                .hygienePurpose(HygienePurpose.Detergent)
//                .productType(ProductType.Hygiene)
//                .build();
//
//        assertThat(productFactoryImplementation.getInstance(hygieneProduct)).isInstanceOf(HygieneProduct.class);
//    };
//}