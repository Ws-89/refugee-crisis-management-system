package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.products.Category;
import com.example.demo.models.products.Product;
import com.example.demo.repo.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.example.demo.models.products.State.Liquid;
import static com.example.demo.models.products.State.Solid;
import static com.example.demo.models.products.Status.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplementationTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImplementation productService;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(productRepository).isNotNull();

        assertThat(productService).isNotNull();
    }

    @Test
    void shouldFindProductById(){
        Category category = Category.builder().categoryName("Medical").attr1Caption("Bandage").build();

        Product product = Product.builder()
                .productId(1L)
                .name("Bandage")
                .expirationDate(LocalDate.of(2017, Month.FEBRUARY,3))
                .description("pressure bandage")
                .amount(200L)
                .weight(200)
                .fragile(false)
                .state(Solid)
                .category(category)
                .reserved(Available)
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductDTO productDTO = productService.findById(product.getProductId());

        assertThat(product.getName()).isEqualTo(productDTO.getName());
        assertThat(product.getDescription()).isEqualTo(productDTO.getDescription());
        assertThat(product.getExpirationDate()).isEqualTo(productDTO.getExpirationDate());
        assertThat(product.getAmount()).isEqualTo(productDTO.getAmount());
        assertThat(product.getWeight()).isEqualTo(productDTO.getWeight());
        assertThat(product.isFragile()).isEqualTo(productDTO.isFragile());
        assertThat(product.getState()).isEqualTo(productDTO.getState());
        assertThat(product.getCategory()).isEqualTo(productDTO.getCategory());
    }

    @Test
    void shouldFindProductByIdThrowsException(){
        Category category = Category.builder().categoryName("Medical").attr1Caption("Bandage").build();

        Product product1 = Product.builder()
                .productId(1L)
                .name("Bandage")
                .expirationDate(LocalDate.of(2017, Month.FEBRUARY,3))
                .description("pressure bandage")
                .amount(200L)
                .weight(200)
                .fragile(false)
                .state(Solid)
                .category(category)
                .reserved(Available)
                .build();

        when(productRepository.findById(1L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> productService.findById(product1.getProductId()));
    }

    @Test
    void shouldFindAllProducts() {
//        Category category = Category.builder().categoryName("Medical").attr1Caption("Bandage").build();
//
//        Product product1 = Product.builder()
//                .productId(1L)
//                .name("Bandage")
//                .expirationDate(LocalDate.of(2017, Month.FEBRUARY,3))
//                .description("pressure bandage")
//                .amount(200L)
//                .weight(200)
//                .fragile(false)
//                .state(Solid)
//                .category(category)
//                .reserved(Available)
//                .build();
//
//        Category category2 = Category.builder().categoryName("Food").attr1Caption("Dairy").build();
//
//        Product product2 = Product.builder()
//                .productId(2L)
//                .name("Coffee")
//                .expirationDate(LocalDate.of(2017, Month.FEBRUARY,3))
//                .description("Black and strong")
//                .amount(200L)
//                .weight(200)
//                .fragile(false)
//                .state(Solid)
//                .category(category)
//                .reserved(Shipped)
//                .build();
//
//        Category category3 = Category.builder().categoryName("Hygiene").attr1Caption("Detergent").build();
//
//        Product product3 = Product.builder()
//                .productId(3L)
//                .name("Cif")
//                .expirationDate(LocalDate.of(2017, Month.FEBRUARY,3))
//                .description("for cleaning house")
//                .amount(200L)
//                .weight(200)
//                .fragile(false)
//                .state(Liquid)
//                .category(category)
//                .reserved(Reserved)
//                .build();
//
//        List<Product> products = Arrays.asList(product1, product2, product3);
//
//        when(productRepository.findAll()).thenReturn(products);
//
//        assertThat(productService.findAllProducts().size()).isEqualTo(3);
    }

    @Test
    void shouldSaveAProduct() {
        Category category = Category.builder().categoryName("Food").attr1Caption("Drink").build();

        Product foodProduct = Product.builder()
                .name("Coffee")
                .expirationDate(LocalDate.of(2017, Month.FEBRUARY,3))
                .description("Black")
                .amount(200L)
                .weight(200)
                .fragile(false)
                .state(Solid)
                .category(category)
                .reserved(Available)
                .build();

        when(productRepository.save(Mockito.any(Product.class))).thenReturn(foodProduct);

        ProductDTO product = productService.saveProduct(foodProduct);


        assertThat(product.getName()).isEqualTo(foodProduct.getName());
        assertThat(product.getDescription()).isEqualTo(foodProduct.getDescription());
        assertThat(product.getExpirationDate()).isEqualTo(foodProduct.getExpirationDate());
        assertThat(product.getAmount()).isEqualTo(foodProduct.getAmount());
        assertThat(product.getWeight()).isEqualTo(foodProduct.getWeight());
        assertThat(product.isFragile()).isEqualTo(foodProduct.isFragile());
        assertThat(product.getState()).isEqualTo(foodProduct.getState());
        assertThat(product.getCategory()).isEqualTo(foodProduct.getCategory());
    }

    @Test
    void shouldUpdateAProduct() {
        Category category = Category.builder().categoryName("Food").attr1Caption("Drink").build();

        Product blackCoffe = Product.builder()
                .productId(1L)
                .name("Coffee")
                .expirationDate(LocalDate.of(2017, Month.FEBRUARY,3))
                .description("Black")
                .amount(200L)
                .weight(200)
                .fragile(false)
                .state(Solid)
                .category(category)
                .reserved(Available)
                .build();

        Product frappe = Product.builder()
                .productId(1L)
                .name("Coffee")
                .expirationDate(LocalDate.of(2017, Month.FEBRUARY,3))
                .description("Black")
                .amount(200L)
                .weight(200)
                .fragile(false)
                .state(Solid)
                .category(category)
                .reserved(Available)
                .build();


        when(productRepository.findById(1L)).thenReturn(Optional.of(blackCoffe));

        ProductDTO frappeDTO = productService.updateProduct(frappe);

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productArgumentCaptor.capture());
        Product productArgumentCaptorValue = productArgumentCaptor.getValue();

        assertThat(productArgumentCaptorValue.getProductId()).isEqualTo(frappe.getProductId());
        assertThat(productArgumentCaptorValue.getName()).isEqualTo(frappe.getName());
        assertThat(productArgumentCaptorValue.getDescription()).isEqualTo(frappe.getDescription());
        assertThat(productArgumentCaptorValue.getExpirationDate()).isEqualTo(frappe.getExpirationDate());
        assertThat(productArgumentCaptorValue.getAmount()).isEqualTo(frappe.getAmount());
        assertThat(productArgumentCaptorValue.getWeight()).isEqualTo(frappe.getWeight());
        assertThat(productArgumentCaptorValue.isFragile()).isEqualTo(frappe.isFragile());
        assertThat(productArgumentCaptorValue.getState()).isEqualTo(frappe.getState());
        assertThat(productArgumentCaptorValue.getCategory()).isEqualTo(frappe.getCategory());
    }

    @Test
    void shouldDeleteAProduct() {
        Category category = Category.builder().categoryName("Food").attr1Caption("Drink").build();

        Product blackCoffe = Product.builder()
                .productId(1L)
                .name("Coffee")
                .expirationDate(LocalDate.of(2017, Month.FEBRUARY,3))
                .description("Black")
                .amount(200L)
                .weight(200)
                .fragile(false)
                .state(Solid)
                .category(category)
                .reserved(Available)
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(blackCoffe));
        productService.deleteProduct(1L);

        verify(productRepository).delete(blackCoffe);
    }

    @Test
    void deleteShouldThrowException(){
        when(productRepository.findById(1L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> productService.deleteProduct(1L));


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