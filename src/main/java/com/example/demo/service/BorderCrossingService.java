package com.example.demo.service;

import com.example.demo.models.*;
import com.example.demo.models.refugees.Refugee;
import com.example.demo.models.shared.Address;
import com.example.demo.models.shared.Contact;
import com.example.demo.models.shared.Person;
import com.example.demo.utils.EmailValidator;
import org.springframework.stereotype.Service;

@Service
public class BorderCrossingService {

    private final EmailValidator emailValidator;
    private final RefugeeService refugeeService;

    public BorderCrossingService(EmailValidator emailValidator, RefugeeService refugeeService) {
        this.emailValidator = emailValidator;
        this.refugeeService = refugeeService;
    }

    public Refugee registerRefugee(RefugeeRegistrationRequest request){

        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("Email is not valid");
        }

        Person person = Person.builder().age(request.getAge()).name(request.getName()).surname(request.getSurname()).nationality(request.getNationality()).build();
        Contact contact = Contact.builder().email(request.getEmail()).telephoneNumber(request.getTelephoneNumber()).build();
        Address address = Address.builder().city(request.getCity()).country(request.getCountry()).street(request.getStreet()).build();

        Refugee refugee = Refugee.builder().person(person).contact(contact).address(address).build();

        return refugeeService.saveRefugee(refugee);

        //TODO
        // sms confirmation

    }

}
