package com.example.demo;

import com.example.demo.controller.CargoController;
import com.example.demo.repo.DeliveryAddressRepository;
//import com.example.demo.repo.HandlingEventRepository;
import com.example.demo.repo.CargoRepository;
import com.example.demo.repo.TransportMovementRepo;
import com.example.demo.service.CargoServiceImplementation;
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
	private CargoRepository cargoRepository;

	@Autowired
	private CargoServiceImplementation productDeliveryServiceImplementation;

	@Autowired
	private CargoController cargoController;

//	@Autowired
//	private HandlingEventRepository handlingEventRepository;

	@Autowired
	private TransportMovementRepo transportMovementRepo;

	@Autowired
	private DeliveryAddressRepository deliveryAddressRepository;

	@Test
	void saveProductDelivery(){
//		ProductDelivery productDelivery = new ProductDelivery();
//		productDelivery.setDescription("First delivery");
//
//		DeliveryHistory history = new DeliveryHistory();
//		HandlingEvent handlingEvent = new HandlingEvent();
//		history.addEvent(handlingEvent);
//		productDelivery.setDeliveryHistory(history);
//
//		DeliverySpecification deliverySpecification = new DeliverySpecification();
//		DeliveryAddress deliveryAddress = new DeliveryAddress();
//		deliveryAddress.setPostCode("123");
//		deliveryAddress.setCity("Bydgoszcz");
//		deliverySpecification.setDeliveryAddress(deliveryAddress);
//		productDelivery.setDeliverySpecification(deliverySpecification);

//		FoodProduct product = new FoodProduct();
//		product.setFoodType(Fruits);
//		productDelivery.addProduct(product);
//		productDeliveryRepository.save(productDelivery);
	}

	@Test
	void SaveEventHandling(){
//
//		Vehicle vehicle = Vehicle.builder()
//				.vehicleCategory("Truck")
//				.capacity(12000.0)
//				.brand("Volvo")
//				.model("Truck")
//				.engine("6.0")
//				.licensePlate("123asdfq")
//				.build();
//
//		DeliveryAddress deliveryAddress = DeliveryAddress.builder()
//				.city("Warszawa")
//				.street("Marszałkowska")
//				.postCode("123")
//				.build();
//
//		DeliverySpecification deliverySpecification = DeliverySpecification.builder()
////				.deliveryAddress(deliveryAddressRepository.save(deliveryAddress))
//				.deliveryAddress(deliveryAddress)
//				.arrivalTime(LocalDateTime.of(2022, Month.DECEMBER, 1, 1, 1, 1, 1))
//				.build();
//
//		DeliveryAddress startingPointAddress = DeliveryAddress.builder()
//				.city("Bydgoszcz")
//				.postCode("123")
//				.street("Gdańska")
//				.build();
//
//		TransportMovement transportMovement = TransportMovement.builder()
//				.startingAddress(startingPointAddress)
//				.deliverySpecification(deliverySpecification)
//				.vehicle(vehicle)
//				.build();
//
//		HandlingEvent handlingEvent = HandlingEvent.builder()
//						.transportMovement(transportMovement)
//								.build();
//
//		ProductDelivery productDelivery = productDeliveryRepository.findById(1L).orElseThrow(() -> new NotFoundException("Not found"));
//		handlingEvent.setDeliveryHistory(productDelivery.getDeliveryHistory());
//		handlingEventRepository.save(handlingEvent);
	}}


