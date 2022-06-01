package com.example.demo;

import com.example.demo.controller.ProductDeliveryController;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.productsdelivery.ProductDelivery;
import com.example.demo.models.productsdelivery.ProductDeliveryStateStarted;
import com.example.demo.repo.MaterialResourceDeliveryRepo;
import com.example.demo.service.ProductDeliveryService;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
//@DataJpaTest
class DemoApplicationTests {

//	@Autowired
//	private RefugeeService refugeeService;

	@InjectMocks

	@Autowired
	private MaterialResourceDeliveryRepo materialResourceDeliveryRepo;

	@Autowired
	private ProductDeliveryService productDeliveryService;

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
	void nextState(){
		ProductDelivery delivery = new ProductDelivery();
		delivery.setState(new ProductDeliveryStateStarted());
		delivery.nextState();
		delivery.nextState();
		assertThat(delivery.getStateName()).isEqualTo("InTransition");
	}



	@Test
	void saveStateToRepo(){
		ProductDelivery delivery = new ProductDelivery();
		delivery.setState(new ProductDeliveryStateStarted());
		delivery.nextState();
		delivery.nextState();
		productDeliveryService.create(delivery);

//		assertThat(delivery.getStateName()).isEqualTo("Started");
	}

	@Test
	void loadStateFromRepo(){
		List<ProductDelivery> lista = materialResourceDeliveryRepo.findAll();
		ProductDelivery delivery  = lista.get(0);
		delivery.nextState();
		materialResourceDeliveryRepo.save(delivery);
//		assertThat(delivery.getStateName()).isEqualTo("Prepared");
	}

	@Test
	void changeState(){
		productDeliveryService.nextState(19L);
	}

	@Test
	void saveFromService(){
		ProductDelivery delivery = materialResourceDeliveryRepo.findById(61L).orElseThrow(()-> new NotFoundException("Not found"));
		delivery.nextState();
		materialResourceDeliveryRepo.save(delivery);
	}


}
