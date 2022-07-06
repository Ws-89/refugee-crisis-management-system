package com.example.demo.productsDelivery;

import com.example.demo.models.productsdelivery.ProductDelivery;
import com.example.demo.models.productsdelivery.ProductDeliveryDTO;
import com.example.demo.repo.ProductDeliveryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ProductsDeliveryTests {

    @Autowired
    ProductDeliveryRepository productDeliveryRepository;

//    @Test
//    void shouldFindProductDeliveryByDescription(){
//
//        ProductDeliveryDTO productDelivery = productDeliveryRepository.findByDescription("1");
//
//        assertThat(productDelivery.getDescription()).isEqualTo("1");
//        assertThat(productDelivery.getDeliveryId()).isEqualTo(56);
//    }

}
