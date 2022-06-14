package com.example.demo.service;

import com.example.demo.models.Refugee;
import com.example.demo.models.lodging.Lodging;
import com.example.demo.repo.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class LodgingService extends GenericService<Lodging> {

    public LodgingService(GenericRepository<Lodging> repository) {
        super(repository);
    }

    public Lodging setStateToAvailable(Long id){
        Lodging lodging = this.get(id);
        lodging.setStateToAvailable();
        return super.repository.save(lodging);
    }

    public Lodging setStateToCanceled(Long id){
        Lodging lodging = this.get(id);
        lodging.setStateToCanceled();
        return super.repository.save(lodging);
    }

    public Lodging addTenant(Long id, Refugee refugee){
        Lodging lodging = this.get(id);
        lodging.addTentant(refugee);
        return super.repository.save(lodging);
    }

    public Lodging removeTenant(Long lodgingId, Long refugeeId){
        Lodging lodging = this.get(lodgingId);
        lodging.removeTenant(refugeeId);
        return super.repository.save(lodging);
    }


}
