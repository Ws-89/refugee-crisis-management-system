package com.example.demo;

import com.example.demo.controller.ProductDeliveryController;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.products.Category;
import com.example.demo.models.products.Product;
import com.example.demo.models.productsdelivery.*;
import com.example.demo.models.vehicles.Vehicle;
import com.example.demo.repo.DeliveryAddressRepository;
import com.example.demo.repo.HandlingEventRepository;
import com.example.demo.repo.ProductDeliveryRepository;
import com.example.demo.repo.TransportMovementRepo;
import com.example.demo.service.ProductDeliveryServiceImplementation;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@Autowired
	private HandlingEventRepository handlingEventRepository;

	@Autowired
	private TransportMovementRepo transportMovementRepo;

	@Autowired
	private DeliveryAddressRepository deliveryAddressRepository;

	@Autowired
	private ProductDeliveryDAO productDeliveryDao;
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
	}


	@Test
	void getListByEntityManager(){
		List<ProductDelivery> list = this.productDeliveryDao.getList();
		System.out.println(list);
	}

	@Test
	void saveByEntityManager(){

		DeliverySpecification deliverySpecification = DeliverySpecification.builder()
				.arrivalTime(LocalDateTime.now())
				.build();

		System.out.println(deliverySpecification);

		ProductDelivery newProductDelivery = new ProductDelivery();
		DeliveryAddress deliveryAddress = DeliveryAddress.builder()
				.city("Bydgoszcz")
				.postCode("85-164")
				.street("Karpacka")
				.build();

		Set<DeliverySpecification> deliverySpecifications = new HashSet<>();
		deliverySpecifications.add(deliverySpecification);

		deliveryAddress.setDeliverySpecifications(deliverySpecifications);
		deliverySpecification.setDeliveryAddress(deliveryAddress);

		newProductDelivery.setDeliverySpecification(deliverySpecification);

		Category category = Category.builder()
				.categoryName("Food")
				.build();

		Product product = Product.builder()
				.description("1")
				.category(category)
				.build();

		Set<Product> products = new HashSet<>();
		products.add(product);

		newProductDelivery.setProducts(products);

		ProductDelivery productDelivery = this.productDeliveryDao.save(newProductDelivery);
		System.out.println(productDelivery);
	}

}
