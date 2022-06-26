package com.example.demo.service;

import com.example.demo.repo.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplementationTest {

    @InjectMocks
    ProductServiceImplementation productService;

    @Mock
    ProductRepository productRepository;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(productRepository).isNotNull();

        assertThat(productService).isNotNull();


    }

    @Test
    void getAllProducs() {

    }

    @Test
    void saveProduct() {
//        ProductDTO foodProduct = ProductDTO.builder()
//                .name("Coffee")
//                .expirationDate(LocalDateTime.of(2017, Month.FEBRUARY,3,6,30,40,50000))
//                .description("Black and strong")
//                .amount(200L)
//                .weight(200)
//                .fragile(false)
//                .state(Solid)
//                .foodType(Drink)
//                .productType(ProductType.Food)
//                .build();
//
//        productService.saveProduct(foodProduct);
//
//        ArgumentCaptor<FoodProduct> foodProductCaptor = ArgumentCaptor.forClass(FoodProduct.class);
//        verify(productRepository).save(foodProductCaptor.capture());
//
//        FoodProduct product = foodProductCaptor.getValue();
//
//        assertThat(product.getName()).isEqualTo(foodProduct.getName());
//        assertThat(product.getDescription()).isEqualTo(foodProduct.getDescription());
//        assertThat(product.getExpirationDate()).isEqualTo(foodProduct.getExpirationDate());
//        assertThat(product.getAmount()).isEqualTo(foodProduct.getAmount());
//        assertThat(product.getWeight()).isEqualTo(foodProduct.getWeight());
//        assertThat(product.isFragile()).isEqualTo(foodProduct.isFragile());
//        assertThat(product.getState()).isEqualTo(foodProduct.getState());
//        assertThat(product.getFoodType()).isEqualTo(foodProduct.getFoodType());
    }

    @Test
    void updateProduct() {
//        ProductDTO foodProduct = ProductDTO.builder()
//                .productId(1L)
//                .name("Bread")
//                .expirationDate(LocalDateTime.of(2017, Month.FEBRUARY,3,6,30,40,50000))
//                .description("Full grain")
//                .amount(200L)
//                .weight(150)
//                .fragile(false)
//                .state(Solid)
//                .foodType(Grains)
//                .productType(ProductType.Food)
//                .build();
//
//        FoodProduct foodProductUpdate = new FoodProduct("Bread", LocalDateTime.of(2017, Month.FEBRUARY,3,6,30,40,50000), "Full grain", 100 , 150L, Status.Available, false, Solid, Grains);
//
//        when(productRepository.findById(1L)).thenReturn(Optional.of(foodProductUpdate));
//        productService.updateProduct(foodProduct);
//
//        ArgumentCaptor<FoodProduct> foodProductCaptor = ArgumentCaptor.forClass(FoodProduct.class);
//        verify(productRepository).save(foodProductCaptor.capture());
//
//        FoodProduct product = foodProductCaptor.getValue();
//
//        assertThat(product.getName()).isEqualTo(foodProduct.getName());
//        assertThat(product.getDescription()).isEqualTo(foodProduct.getDescription());
//        assertThat(product.getExpirationDate()).isEqualTo(foodProduct.getExpirationDate());
//        assertThat(product.getAmount()).isEqualTo(foodProduct.getAmount());
//        assertThat(product.getWeight()).isEqualTo(foodProduct.getWeight());
//        assertThat(product.isFragile()).isEqualTo(foodProduct.isFragile());
//        assertThat(product.getState()).isEqualTo(foodProduct.getState());
//        assertThat(product.getFoodType()).isEqualTo(foodProduct.getFoodType());
    }

    @Test
    void deleteProduct() {
//        FoodProduct foodProductUpdate = new FoodProduct("Bread", LocalDateTime.of(2017, Month.FEBRUARY,3,6,30,40,50000), "Full grain", 150 , 200L, Status.Available, false, Solid, Grains);
//
//        when(productRepository.findById(1L)).thenReturn(Optional.of(foodProductUpdate));
//        productService.deleteProduct(1L);
//
//        ArgumentCaptor<FoodProduct> foodProductCaptor = ArgumentCaptor.forClass(FoodProduct.class);
//        verify(productRepository).delete(foodProductCaptor.capture());
//
//        FoodProduct product = foodProductCaptor.getValue();
//
//        assertThat(product.getName()).isEqualTo(foodProductUpdate.getName());
//        assertThat(product.getDescription()).isEqualTo(foodProductUpdate.getDescription());
//        assertThat(product.getExpirationDate()).isEqualTo(foodProductUpdate.getExpirationDate());
//        assertThat(product.getAmount()).isEqualTo(foodProductUpdate.getAmount());
//        assertThat(product.getWeight()).isEqualTo(foodProductUpdate.getWeight());
//        assertThat(product.isFragile()).isEqualTo(foodProductUpdate.isFragile());
//        assertThat(product.getState()).isEqualTo(foodProductUpdate.getState());
//        assertThat(product.getFoodType()).isEqualTo(foodProductUpdate.getFoodType());
    }

    @Test
    void findAllProducts() {

    }

    @Test
    void findAllHygieneProducts() {

    }

    @Test
    void findAllFoodProducts() {

    }

    @Test
    void findAllMedicalProducts() {

    }
}