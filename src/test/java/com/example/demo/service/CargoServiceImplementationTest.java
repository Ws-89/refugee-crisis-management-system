package com.example.demo.service;

import com.example.demo.dto.CargoDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.products.*;
import com.example.demo.models.cargo.*;
import com.example.demo.repo.DeliveryAddressRepository;
import com.example.demo.repo.CargoRepository;
import com.example.demo.repo.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static com.example.demo.models.products.State.Solid;
import static com.example.demo.models.products.Status.Available;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CargoServiceImplementationTest {

    @Mock
    private CargoRepository cargoRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private DeliveryAddressRepository deliveryAddressRepository;

   @InjectMocks
   CargoServiceImplementation productDeliveryService;

    @Test
    void shouldFindAllProductDeliveries() {
        DeliveryAddress startingAddress = DeliveryAddress.builder().city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliveryAddress deliveryAddress = DeliveryAddress.builder().city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliverySpecification deliverySpecification = DeliverySpecification.builder().deliveryAddress(deliveryAddress).build();
        Cargo cargo = Cargo.builder().deliverySpecification(deliverySpecification).startingAddress(startingAddress).build();
        Cargo cargo2 = Cargo.builder().deliverySpecification(deliverySpecification).startingAddress(startingAddress).build();
        Cargo cargo3 = Cargo.builder().deliverySpecification(deliverySpecification).startingAddress(startingAddress).build();
        Cargo cargo4 = Cargo.builder().deliverySpecification(deliverySpecification).startingAddress(startingAddress).build();
        Cargo cargo5 = Cargo.builder().deliverySpecification(deliverySpecification).startingAddress(startingAddress).build();

        List<Cargo> cargoList = Arrays.asList(cargo, cargo2, cargo3, cargo4, cargo5);

        when(cargoRepository.findAll()).thenReturn(cargoList);

//        assertThat(productDeliveryService.findByDescriptionContaining("", 0, 10).size()).isEqualTo(5);

    }

    @Test
    void shouldFindProductById() {
        DeliveryAddress startingAddress = DeliveryAddress.builder().city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliveryAddress deliveryAddress = DeliveryAddress.builder().city("Qwerty").postCode("12-345").state("Zxcv").street("Fghjk").build();
        DeliverySpecification deliverySpecification = DeliverySpecification.builder().deliveryAddress(deliveryAddress).build();
        Cargo cargo = Cargo.builder().cargoId(1L).deliverySpecification(deliverySpecification).startingAddress(startingAddress).build();

        when(cargoRepository.findById(1L)).thenReturn(Optional.of(cargo));

        CargoDTO cargoDTO = productDeliveryService.findById(1L);

        assertThat(cargoDTO.getDeliverySpecification().getDeliveryAddress().getCity())
                .isEqualTo(cargo.getDeliverySpecification().getDeliveryAddress().getCity());
        assertThat(cargoDTO.getStartingAddress().getStreet()).isEqualTo(cargo.getStartingAddress().getStreet());
    }

    @Test
    void findProductDeliveryByIdShouldThrowException(){
        when(cargoRepository.findById(1L)).thenThrow(NotFoundException.class);

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
        Cargo cargo = Cargo.builder().cargoId(1L)
                .startingAddress(startingAddress)
                .deliverySpecification(deliverySpecification)
                .description("Some snacks")
                .totalWeight(230.0)
                .build();


        when(deliveryAddressRepository.findById(1L)).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> productDeliveryService.saveCargo(cargo));
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
        Cargo cargo = Cargo.builder().cargoId(1L)
                .startingAddress(startingAddress)
                .deliverySpecification(deliverySpecification)
                .description("Some snacks")
                .totalWeight(230.0)
                .build();

        productDeliveryService.saveCargo(cargo);

        ArgumentCaptor<Cargo> productDeliveryCaptor = ArgumentCaptor.forClass(Cargo.class);
        verify(cargoRepository).save(productDeliveryCaptor.capture());

        Cargo capturedCargo = productDeliveryCaptor.getValue();

        assertThat(capturedCargo.getProducts()).isEqualTo(cargo.getProducts());

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
        Cargo cargo = Cargo.builder().cargoId(1L)
                .startingAddress(startingAddress)
                .deliverySpecification(deliverySpecification)
                .description("Some snacks")
                .totalWeight(230.0)
                .build();

        when(cargoRepository.findById(1L)).thenReturn(Optional.of(cargo));
        productDeliveryService.deleteCargo(1L);

        verify(cargoRepository).delete(cargo);
    }

}