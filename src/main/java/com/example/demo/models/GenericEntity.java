package com.example.demo.models;

public interface GenericEntity<T> {

    void update(T source);

    Long getId();

    T createNewInstance();

}
