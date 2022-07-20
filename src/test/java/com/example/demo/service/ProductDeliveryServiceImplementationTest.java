package com.example.demo.service;

import com.example.demo.dto.ProductDeliveryDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.products.*;
import com.example.demo.models.productsdelivery.*;
import com.example.demo.repo.DeliveryAddressRepository;
import com.example.demo.repo.ProductDeliveryRepository;
import com.example.demo.repo.ProductRepository;
import org.hibernate.Session;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static com.example.demo.models.products.State.Solid;
import static com.example.demo.models.products.Status.Available;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductDeliveryServiceImplementationTest {

    @Mock
    private ProductDeliveryRepository productDeliveryRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private DeliveryAddressRepository deliveryAddressRepository;

   @InjectMocks
   ProductDeliveryServiceImplementation productDeliveryService;

    @Test
    void shouldFindAllProductDeliveries() {
        DeliveryAddress startingAddress = DeliveryAddress.builder().city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliveryAddress deliveryAddress = DeliveryAddress.builder().city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliverySpecification deliverySpecification = DeliverySpecification.builder().deliveryAddress(deliveryAddress).build();
        ProductDelivery productDelivery = ProductDelivery.builder().deliverySpecification(deliverySpecification).startingAddress(startingAddress).build();
        ProductDelivery productDelivery2 = ProductDelivery.builder().deliverySpecification(deliverySpecification).startingAddress(startingAddress).build();
        ProductDelivery productDelivery3 = ProductDelivery.builder().deliverySpecification(deliverySpecification).startingAddress(startingAddress).build();
        ProductDelivery productDelivery4 = ProductDelivery.builder().deliverySpecification(deliverySpecification).startingAddress(startingAddress).build();
        ProductDelivery productDelivery5 = ProductDelivery.builder().deliverySpecification(deliverySpecification).startingAddress(startingAddress).build();

        List<ProductDelivery> productDeliveryList = Arrays.asList(productDelivery,productDelivery2,productDelivery3,productDelivery4,productDelivery5);

        when(productDeliveryRepository.findAll()).thenReturn(productDeliveryList);

        assertThat(productDeliveryService.findAllProductDelivery().size()).isEqualTo(5);

    }

    @Test
    void shouldFindProductById() {
        DeliveryAddress startingAddress = DeliveryAddress.builder().city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliveryAddress deliveryAddress = DeliveryAddress.builder().city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliverySpecification deliverySpecification = DeliverySpecification.builder().deliveryAddress(deliveryAddress).build();
        ProductDelivery productDelivery = ProductDelivery.builder().deliveryId(1L).deliverySpecification(deliverySpecification).startingAddress(startingAddress).build();

        when(productDeliveryRepository.findById(1L)).thenReturn(Optional.of(productDelivery));

        ProductDeliveryDTO productDeliveryDTO = productDeliveryService.findById(1L);

        assertThat(productDeliveryDTO.getDeliverySpecification().getDeliveryAddress().getCity())
                .isEqualTo(productDelivery.getDeliverySpecification().getDeliveryAddress().getCity());
        assertThat(productDeliveryDTO.getStartingAddress().getStreet()).isEqualTo(productDelivery.getStartingAddress().getStreet());
    }

    @Test
    void findProductDeliveryByIdShouldThrowException(){
        when(productDeliveryRepository.findById(1L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> productDeliveryService.findById(1L));
    }

    @Test
    void saveProductDeliveryShouldThrowException(){
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
                .productId(2L)
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

        List<Product> products = Arrays.asList(blackCoffe, frappe);

        DeliveryAddress startingAddress = DeliveryAddress.builder().deliveryAddressId(1L).city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliveryAddress deliveryAddress = DeliveryAddress.builder().city("Qwerty").deliveryAddressId(1L).postCode("12-345").state("Zxcv").street("Fghjk").build();

        DeliverySpecification deliverySpecification = DeliverySpecification.builder().deliveryAddress(deliveryAddress).build();
        ProductDelivery productDelivery = ProductDelivery.builder().deliveryId(1L)
                .startingAddress(startingAddress)
                .deliverySpecification(deliverySpecification)
                .description("Some snacks")
                .totalWeight(230.0)
                .build();


        when(deliveryAddressRepository.findById(1L)).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> productDeliveryService.saveProductDelivery(productDelivery));
    }

    @Test
    void shouldSaveProductDelivery() {

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
                .productId(2L)
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

        List<Product> products = Arrays.asList(blackCoffe, frappe);

        DeliveryAddress startingAddress = DeliveryAddress.builder().deliveryAddressId(1L).city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliveryAddress deliveryAddress = DeliveryAddress.builder().city("Qwerty").deliveryAddressId(2L).postCode("12-345").state("Zxcv").street("Fghjk").build();

        when(deliveryAddressRepository.findById(1L)).thenReturn(Optional.of(startingAddress));
        when(deliveryAddressRepository.findById(2L)).thenReturn(Optional.of(deliveryAddress));

        DeliverySpecification deliverySpecification = DeliverySpecification.builder().deliveryAddress(deliveryAddress).build();
        ProductDelivery productDelivery = ProductDelivery.builder().deliveryId(1L)
                .startingAddress(startingAddress)
                .deliverySpecification(deliverySpecification)
                .description("Some snacks")
                .totalWeight(230.0)
                .build();

        productDeliveryService.saveProductDelivery(productDelivery);

        ArgumentCaptor<ProductDelivery> productDeliveryCaptor = ArgumentCaptor.forClass(ProductDelivery.class);
        verify(productDeliveryRepository).save(productDeliveryCaptor.capture());

        ProductDelivery capturedProductDelivery = productDeliveryCaptor.getValue();

        assertThat(capturedProductDelivery.getProducts()).isEqualTo(productDelivery.getProducts());

    }

    @Test
    void updateProductDeliveryShouldPass() {
//        Category category = Category.builder().categoryName("Food").attr1Caption("Drink").build();
//
//        Product blackCoffe = Product.builder()
//                .productId(1L)
//                .name("Coffee")
//                .expirationDate(LocalDate.of(2017, Month.FEBRUARY,3))
//                .description("Black")
//                .amount(200L)
//                .weight(200)
//                .fragile(false)
//                .state(Solid)
//                .category(category)
//                .reserved(Available)
//                .build();
//
//        Product frappe = Product.builder()
//                .productId(2L)
//                .name("Coffee")
//                .expirationDate(LocalDate.of(2017, Month.FEBRUARY,3))
//                .description("Black")
//                .amount(200L)
//                .weight(200)
//                .fragile(false)
//                .state(Solid)
//                .category(category)
//                .reserved(Available)
//                .build();
//
//        List<Product> products = Arrays.asList(blackCoffe, frappe);
//
//        DeliveryAddress startingAddress = DeliveryAddress.builder().deliveryAddressId(1L).city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
//        DeliveryAddress deliveryAddress = DeliveryAddress.builder().city("Qwerty").deliveryAddressId(2L).postCode("12-345").state("Zxcv").street("Fghjk").build();
//
//
//        DeliverySpecification deliverySpecification = DeliverySpecification.builder().deliveryAddress(deliveryAddress).build();
//        ProductDelivery productDelivery = ProductDelivery.builder().deliveryId(1L)
//                .startingAddress(startingAddress)
//                .deliverySpecification(deliverySpecification)
//                .products(products)
//                .description("Coffee")
//                .totalWeight(230.0)
//                .build();
//
//        ProductDelivery updatedProductDelivery = ProductDelivery.builder().deliveryId(1L)
//                .startingAddress(startingAddress)
//                .deliverySpecification(deliverySpecification)
//                .description("Coffee")
//                .products(products)
//                .totalWeight(250.0)
//                .build();
//
//        when(productDeliveryRepository.findById(1L)).thenReturn(Optional.of(productDelivery));
//
//        productDeliveryService.updateProductDelivery(updatedProductDelivery);
//
//        ArgumentCaptor<ProductDelivery> productDeliveryCaptor = ArgumentCaptor.forClass(ProductDelivery.class);
//        verify(productDeliveryRepository).save(productDeliveryCaptor.capture());
//        ProductDelivery capturedProductDelivery = productDeliveryCaptor.getValue();
//
//        assertThat(capturedProductDelivery.getTotalWeight()).isEqualTo(250.0);
    }

    @Test
    void deleteProductDelivery() {
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
                .productId(2L)
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

        List<Product> products = Arrays.asList(blackCoffe, frappe);

        DeliveryAddress startingAddress = DeliveryAddress.builder().deliveryAddressId(1L).city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliveryAddress deliveryAddress = DeliveryAddress.builder().city("Qwerty").deliveryAddressId(2L).postCode("12-345").state("Zxcv").street("Fghjk").build();

        DeliverySpecification deliverySpecification = DeliverySpecification.builder().deliveryAddress(deliveryAddress).build();
        ProductDelivery productDelivery = ProductDelivery.builder().deliveryId(1L)
                .startingAddress(startingAddress)
                .deliverySpecification(deliverySpecification)
                .description("Some snacks")
                .totalWeight(230.0)
                .build();

        when(productDeliveryRepository.findById(1L)).thenReturn(Optional.of(productDelivery));
        productDeliveryService.deleteProductDelivery(1L);

        verify(productDeliveryRepository).delete(productDelivery);
    }

}