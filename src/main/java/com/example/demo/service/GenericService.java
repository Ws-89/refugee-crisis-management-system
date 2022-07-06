package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.models.GenericEntity;
import com.example.demo.repo.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericService<T extends GenericEntity<T>>{

    protected final GenericRepository<T> repository;

    public GenericService(GenericRepository<T> repository) {
        this.repository = repository;
    }

    public Page<T> getPage(Pageable pageable){
        return this.repository.findAll(pageable);
    }

    public T get(Long id){
        return repository.findById(id).orElseThrow(()-> new NotFoundException("Object with id %id found"));
    }

    @Transactional
    public T update(T updated){
        T dbDomain = get(updated.getId());
        dbDomain.update(updated);
        return repository.save(dbDomain);
    }

    @Transactional
    public T create(T newDomain){
        T dbDomain = newDomain.createNewInstance();
        return repository.save(dbDomain);
    }

    @Transactional
    public void delete(Long id){
        // check if object with this id exist
        get(id);
        repository.deleteById(id);
    }
}
