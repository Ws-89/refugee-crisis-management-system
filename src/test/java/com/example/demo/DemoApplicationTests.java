package com.example.demo;

import com.example.demo.models.shared.Address;
import com.example.demo.models.shared.Contact;
import com.example.demo.models.shared.Person;
import com.example.demo.models.refugees.Refugee;
import com.example.demo.service.RefugeeService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
//@DataJpaTest
class DemoApplicationTests {

	@Autowired
	private RefugeeService refugeeService;

	@Test
	void refugeeSave(){
		Refugee refugee = Refugee.builder()
				.person(Person.builder().name("John").surname("Doe").age(99).nationality("Some nationality").build())
				.address(Address.builder().city("Some other city").street("Some street").country("Some country").build())
				.contact(Contact.builder().email("some@wp.pl").telephoneNumber("123456789").build())
				.build();

		refugeeService.saveRefugee(refugee);
	}




}
