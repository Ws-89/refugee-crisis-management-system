package com.example.demo;

import com.example.demo.controller.MaterialResourceDeliveryController;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.materialResourceDelivery.MaterialResourceDelivery;
import com.example.demo.models.materialResourceDelivery.MaterialResourceDeliveryStateStarted;
import com.example.demo.repo.MaterialResourceDeliveryRepo;
import com.example.demo.service.MaterialResourceDeliveryService;
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
	private MaterialResourceDeliveryService materialResourceDeliveryService;

	@Autowired
	private MaterialResourceDeliveryController materialResourceDeliveryController;

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
		MaterialResourceDelivery delivery = new MaterialResourceDelivery();
		delivery.setState(new MaterialResourceDeliveryStateStarted());
		delivery.nextState();
		delivery.nextState();
		assertThat(delivery.getStateName()).isEqualTo("InTransition");
	}



	@Test
	void saveStateToRepo(){
		MaterialResourceDelivery delivery = new MaterialResourceDelivery();
		delivery.setState(new MaterialResourceDeliveryStateStarted());
		delivery.nextState();
		delivery.nextState();
		materialResourceDeliveryService.sendDelivery(delivery);

//		assertThat(delivery.getStateName()).isEqualTo("Started");
	}

	@Test
	void loadStateFromRepo(){
		List<MaterialResourceDelivery> lista = materialResourceDeliveryRepo.findAll();
		MaterialResourceDelivery delivery  = lista.get(0);
		delivery.nextState();
		materialResourceDeliveryRepo.save(delivery);
//		assertThat(delivery.getStateName()).isEqualTo("Prepared");
	}

	@Test
	void changeState(){
		materialResourceDeliveryService.nextState(19L);
	}

	@Test
	void saveFromService(){
		MaterialResourceDelivery delivery = materialResourceDeliveryRepo.findById(61L).orElseThrow(()-> new NotFoundException("Not found"));
		delivery.nextState();
		materialResourceDeliveryRepo.save(delivery);
	}


}
