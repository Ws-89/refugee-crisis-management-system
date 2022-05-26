package com.example.demo.service;

import com.example.demo.models.shared.Address;
import com.example.demo.models.shared.Contact;
import com.example.demo.models.HelpCenter;
import com.example.demo.models.HelpCenterRequest;
import com.example.demo.repo.HelpCenterRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class HelpCenterService {

    private final HelpCenterRepo helpCenterRepo;

    public HelpCenterService(HelpCenterRepo helpCenterRepo) {
        this.helpCenterRepo = helpCenterRepo;
    }

    public HelpCenter saveHelpCenter(HelpCenterRequest request){

        Address address = Address.builder().street(request.getStreet()).country(request.getCountry()).city(request.getCity()).build();
        Contact contact = Contact.builder().email(request.getEmail()).telephoneNumber(request.getTelephoneNumber()).build();

        HelpCenter helpCenter = HelpCenter.builder().address(address).contact(contact).build();

        return this.helpCenterRepo.save(helpCenter);
    }

    public HelpCenter getHelpCenterById(Long id){
        return this.helpCenterRepo.findById(id).get();
    }

    public Collection<HelpCenter> getAllHelpCenter(){
        return this.helpCenterRepo.findAll();
    }

    public HelpCenter updateHelpCenter(HelpCenter helpCenter){
        return this.helpCenterRepo.save(helpCenter);
    }

    public boolean deleteHelpCenter(HelpCenter helpCenter){
        this.helpCenterRepo.deleteById(helpCenter.getId());
        return true;
    }
}
