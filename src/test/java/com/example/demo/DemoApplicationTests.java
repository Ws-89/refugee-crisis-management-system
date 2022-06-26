package com.example.demo;

import com.example.demo.controller.ProductDeliveryController;
import com.example.demo.models.productsdelivery.*;
import com.example.demo.repo.ProductDeliveryRepository;
import com.example.demo.service.ProductDeliveryServiceImplementation;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
//@DataJpaTest
class DemoApplicationTests {

//	@Autowired
//	private RefugeeService refugeeService;

//	@InjectMocks


	@Autowired
	private ProductDeliveryRepository productDeliveryRepository;

	@Autowired
	private ProductDeliveryServiceImplementation productDeliveryServiceImplementation;

	@Autowired
	private ProductDeliveryController productDeliveryController;

//	@Test
//	void refugeeSave(){
//		Refugee refugee = Refugee.builder()
//				.person(Person.builder().name("John").surname("Doe").age(99).nationality("Some nationality").build())
//				.address(Address.builder().city("Some other city").street("Some street").country("Some country").build())
//				.contact(Contact.builder().email("some@wp.pl").telephoneNumber("123456789").build())
//				.build();
//
//		refugeeService.saveRefugee(refugee);
//	}

	@Test
	void saveProductDelivery(){
		ProductDelivery productDelivery = new ProductDelivery();
		productDelivery.setDescription("First delivery");
		productDelivery.setCapacity(10.0);

		DeliveryHistory history = new DeliveryHistory();
		HandlingEvent handlingEvent = new HandlingEvent();
		history.addEvent(handlingEvent);
		productDelivery.setDeliveryHistory(history);

		DeliverySpecification deliverySpecification = new DeliverySpecification();
		DeliveryAddress deliveryAddress = new DeliveryAddress();
		deliveryAddress.setPostCode("123");
		deliveryAddress.setCity("Bydgoszcz");
		deliverySpecification.setDeliveryAddress(deliveryAddress);
		productDelivery.setDeliverySpecification(deliverySpecification);

//		FoodProduct product = new FoodProduct();
//		product.setFoodType(Fruits);
//		productDelivery.addProduct(product);
//		productDeliveryRepository.save(productDelivery);
	}




}
